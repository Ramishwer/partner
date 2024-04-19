package com.goev.partner.service.partner.impl;

import com.goev.lib.exceptions.ResponseException;
import com.goev.lib.utilities.ApplicationContext;
import com.goev.partner.dao.location.LocationDao;
import com.goev.partner.dao.partner.detail.PartnerDao;
import com.goev.partner.dao.partner.detail.PartnerDetailDao;
import com.goev.partner.dao.partner.document.PartnerDocumentDao;
import com.goev.partner.dao.partner.document.PartnerDocumentTypeDao;
import com.goev.partner.dto.common.PageDto;
import com.goev.partner.dto.common.PaginatedResponseDto;
import com.goev.partner.dto.location.LocationDto;
import com.goev.partner.dto.partner.detail.PartnerDetailsDto;
import com.goev.partner.dto.partner.detail.PartnerDto;
import com.goev.partner.dto.partner.document.PartnerDocumentDto;
import com.goev.partner.dto.partner.document.PartnerDocumentTypeDto;
import com.goev.partner.repository.location.LocationRepository;
import com.goev.partner.repository.partner.detail.PartnerDetailRepository;
import com.goev.partner.repository.partner.detail.PartnerRepository;
import com.goev.partner.repository.partner.document.PartnerDocumentRepository;
import com.goev.partner.repository.partner.document.PartnerDocumentTypeRepository;
import com.goev.partner.service.partner.PartnerService;
import com.goev.partner.utilities.S3Utils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class PartnerServiceImpl implements PartnerService {
    private final PartnerRepository partnerRepository;
    private final PartnerDocumentTypeRepository partnerDocumentTypeRepository;
    private final PartnerDocumentRepository partnerDocumentRepository;
    private final PartnerDetailRepository partnerDetailRepository;
    private final LocationRepository locationRepository;

    private final S3Utils s3;

    @Override
    public PartnerDetailsDto getPartnerDetails(String partnerUUID) {
        PartnerDao partner = partnerRepository.findByAuthUUID(ApplicationContext.getAuthUUID());
        if (partner == null)
            throw new ResponseException("No partner found for Id :" + partnerUUID);

        PartnerDetailDao partnerDetailDao = partnerDetailRepository.findById(partner.getPartnerDetailsId());

        if (partnerDetailDao == null)
            throw new ResponseException("No partner details found for Id :" + partnerUUID);


        PartnerDetailsDto result = PartnerDetailsDto.builder().build();
        setPartnerDetails(result, partner, partnerDetailDao);
        setPartnerHomeLocation(result, partnerDetailDao.getHomeLocationId());
        setPartnerDocuments(result, partner.getId());

        return result;
    }

    @Override
    public PartnerDocumentDto createDocument(String partnerUUID, PartnerDocumentDto partnerDocumentDto) {
        PartnerDao partnerDao = partnerRepository.findByUUID(partnerUUID);
        if (partnerDao == null)
            throw new ResponseException("No partner found for id :" + partnerUUID);

        PartnerDocumentDao partnerDocumentDao = new PartnerDocumentDao();

        if (partnerDocumentDto.getType() == null || partnerDocumentDto.getType().getUuid() == null)
            throw new ResponseException("Error in saving partner model: Invalid Manufacturer");
        PartnerDocumentTypeDao partnerDocumentTypeDao = partnerDocumentTypeRepository.findByUUID(partnerDocumentDto.getType().getUuid());

        if (partnerDocumentTypeDao == null || partnerDocumentTypeDao.getId() == null)
            throw new ResponseException("Error in saving partner document: Invalid Document Type");

        partnerDocumentDao.setUrl(s3.getUrlForPath(partnerDocumentDto.getUrl(),partnerDocumentTypeDao.getS3Key()));
        partnerDocumentDao.setStatus(partnerDocumentDto.getStatus());
        partnerDocumentDao.setDescription(partnerDocumentDto.getDescription());
        partnerDocumentDao.setFileName(partnerDocumentDto.getFileName());
        partnerDocumentDao.setPartnerId(partnerDao.getId());
        partnerDocumentDao.setPartnerDocumentTypeId(partnerDocumentTypeDao.getId());
        partnerDocumentDao = partnerDocumentRepository.save(partnerDocumentDao);
        if (partnerDocumentDao == null)
            throw new ResponseException("Error in saving partner document");
        return PartnerDocumentDto.builder()
                .uuid(partnerDocumentDto.getUuid())
                .type(PartnerDocumentTypeDto.builder()
                        .uuid(partnerDocumentTypeDao.getUuid())
                        .label(partnerDocumentTypeDao.getLabel())
                        .name(partnerDocumentTypeDao.getName())
                        .build())
                .fileName(partnerDocumentTypeDao.getName())
                .description(partnerDocumentDao.getDescription())
                .status(partnerDocumentDao.getStatus())
                .url(partnerDocumentDao.getUrl())
                .build();
    }

    @Override
    public PaginatedResponseDto<PartnerDocumentDto> getDocuments(String partnerUUID) {
        PartnerDao partnerDao = partnerRepository.findByUUID(partnerUUID);
        if (partnerDao == null)
            throw new ResponseException("No partner found for id :" + partnerUUID);

        List<PartnerDocumentTypeDao> activeDocumentTypes = partnerDocumentTypeRepository.findAll();
        if (CollectionUtils.isEmpty(activeDocumentTypes))
            return PaginatedResponseDto.<PartnerDocumentDto>builder().pagination(PageDto.builder().currentPage(0).totalPages(0).build()).elements(new ArrayList<>()).build();

        Map<Integer, PartnerDocumentTypeDao> documentTypeIdToDocumentTypeMap = activeDocumentTypes.stream()
                .collect(Collectors.toMap(PartnerDocumentTypeDao::getId, Function.identity()));
        List<Integer> activeDocumentTypeIds = activeDocumentTypes.stream().map(PartnerDocumentTypeDao::getId).toList();

        Map<Integer, PartnerDocumentDao> existingDocumentMap = partnerDocumentRepository.getExistingDocumentMap(partnerDao.getId(), activeDocumentTypeIds);
        List<PartnerDocumentDto> documentList = PartnerDetailsDto.getPartnerDocumentDtoList(documentTypeIdToDocumentTypeMap, existingDocumentMap);
        return PaginatedResponseDto.<PartnerDocumentDto>builder().elements(documentList).build();
    }

    private void setPartnerDetails(PartnerDetailsDto result, PartnerDao partnerDao, PartnerDetailDao partnerDetails) {
        PartnerDto partnerDto = new PartnerDto();
        partnerDto.setPunchId(partnerDao.getPunchId());
        partnerDto.setState(partnerDao.getState());
        partnerDto.setUuid(partnerDao.getUuid());
        partnerDto.setPhoneNumber(partnerDao.getPhoneNumber());
        partnerDto.setAuthUUID(partnerDao.getAuthUuid());

        result.setDetails(partnerDto);


        if(partnerDetails == null)
            return;

        result.setJoiningDate(partnerDetails.getJoiningDate());
        result.setDlNumber(partnerDetails.getDlNumber());
        result.setDlExpiry(partnerDetails.getDlExpiry());
        result.setRemark(partnerDetails.getRemark());
        result.setOnboardingDate(partnerDetails.getOnboardingDate());
        result.setDeboardingDate(partnerDetails.getDeboardingDate());
        result.setUuid(partnerDao.getUuid());
        result.setDriverTestStatus(partnerDetails.getDriverTestStatus());
        result.setInterviewDate(partnerDetails.getInterviewDate());
        result.setSelectionStatus(partnerDetails.getSelectionStatus());
        result.setIsVerified(partnerDetails.getIsVerified());
        result.setSourceOfLead(partnerDetails.getSourceOfLead());
        result.setSourceOfLeadType(partnerDetails.getSourceOfLeadType());
        result.getDetails().setFirstName(partnerDetails.getFirstName());
        result.getDetails().setLastName(partnerDetails.getLastName());
        result.getDetails().setFathersName(partnerDetails.getFathersName());
        result.getDetails().setLocalAddress(partnerDetails.getLocalAddress());
        result.getDetails().setPermanentAddress(partnerDetails.getPermanentAddress());
    }


    private void setPartnerHomeLocation(PartnerDetailsDto result, Integer homeLocationId) {
        LocationDao locationDao = locationRepository.findById(homeLocationId);
        if (locationDao == null)
            return;
        result.setHomeLocation(LocationDto.builder()
                .latitude(locationDao.getLatitude())
                .longitude(locationDao.getLongitude())
                .name(locationDao.getName())
                .type(locationDao.getType())
                .uuid(locationDao.getUuid())
                .build());
    }

    private void setPartnerDocuments(PartnerDetailsDto result, Integer partnerId) {
        List<PartnerDocumentTypeDao> activeDocumentTypes = partnerDocumentTypeRepository.findAll();
        if (CollectionUtils.isEmpty(activeDocumentTypes))
            return;
        Map<Integer, PartnerDocumentTypeDao> documentTypeIdToDocumentTypeMap = activeDocumentTypes.stream()
                .collect(Collectors.toMap(PartnerDocumentTypeDao::getId, Function.identity()));
        List<Integer> activeDocumentTypeIds = activeDocumentTypes.stream().map(PartnerDocumentTypeDao::getId).toList();


        Map<Integer, PartnerDocumentDao> existingDocumentMap = partnerDocumentRepository.getExistingDocumentMap(partnerId, activeDocumentTypeIds);
        List<PartnerDocumentDto> documentList = PartnerDetailsDto.getPartnerDocumentDtoList(documentTypeIdToDocumentTypeMap, existingDocumentMap);
        result.setDocuments(documentList);
    }



}

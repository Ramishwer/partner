package com.goev.partner.service.partner.impl;

import com.goev.lib.dto.LatLongDto;
import com.goev.lib.exceptions.ResponseException;
import com.goev.lib.utilities.ApplicationContext;
import com.goev.partner.dao.location.LocationDao;
import com.goev.partner.dao.partner.detail.PartnerDao;
import com.goev.partner.dao.partner.detail.PartnerDetailDao;
import com.goev.partner.dto.location.LocationDto;
import com.goev.partner.dto.partner.PartnerViewDto;
import com.goev.partner.dto.partner.detail.PartnerDetailDto;
import com.goev.partner.dto.partner.detail.PartnerDto;
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
    public PartnerDetailDto getPartnerDetails(String partnerUUID) {
        PartnerDao partner = partnerRepository.findByAuthUUID(ApplicationContext.getAuthUUID());
        if (partner == null)
            throw new ResponseException("No partner found for Id :" + partnerUUID);

        PartnerDetailDao partnerDetailDao = partnerDetailRepository.findById(partner.getPartnerDetailsId());

        if (partnerDetailDao == null)
            throw new ResponseException("No partner details found for Id :" + partnerUUID);


        PartnerDetailDto result = PartnerDetailDto.builder().build();
        setPartnerDetails(result, partner, partnerDetailDao);
        setPartnerHomeLocation(result, partnerDetailDao.getHomeLocationId());

        return result;
    }

    @Override
    public PartnerDetailDto updatePartnerDetails(String partnerUUID, PartnerDto partnerDto) {
        return null;
    }


    private void setPartnerDetails(PartnerDetailDto result, PartnerDao partnerDao, PartnerDetailDao partnerDetails) {
        PartnerViewDto partnerDto = new PartnerViewDto();
        partnerDto.setPunchId(partnerDao.getPunchId());
        partnerDto.setState(partnerDao.getState());
        partnerDto.setUuid(partnerDao.getUuid());
        partnerDto.setPhoneNumber(partnerDao.getPhoneNumber());
        partnerDto.setFirstName(partnerDetails.getFirstName());
        partnerDto.setLastName(partnerDetails.getLastName());
        result.setPartner(partnerDto);


        if (partnerDetails == null)
            return;

        result.setJoiningDate(partnerDetails.getJoiningDate());
        result.setDlNumber(partnerDetails.getDlNumber());
        result.setDlExpiryDate(partnerDetails.getDlExpiryDate());
        result.setRemark(partnerDetails.getRemark());
        result.setOnboardingDate(partnerDetails.getOnboardingDate());
        result.setDeboardingDate(partnerDetails.getDeboardingDate());
        result.setUuid(partnerDao.getUuid());
        result.setDrivingTestStatus(partnerDetails.getDrivingTestStatus());
        result.setInterviewDate(partnerDetails.getInterviewDate());
        result.setSelectionStatus(partnerDetails.getSelectionStatus());
        result.setIsVerified(partnerDetails.getIsVerified());
        result.setSourceOfLead(partnerDetails.getSourceOfLead());
        result.setSourceOfLeadType(partnerDetails.getSourceOfLeadType());
        result.setFathersName(partnerDetails.getFathersName());
        result.setLocalAddress(partnerDetails.getLocalAddress());
        result.setPermanentAddress(partnerDetails.getPermanentAddress());
    }


    private void setPartnerHomeLocation(PartnerDetailDto result, Integer homeLocationId) {
        LocationDao locationDao = locationRepository.findById(homeLocationId);
        if (locationDao == null)
            return;
        result.setHomeLocation(LocationDto.builder()
                .coordinates(LatLongDto.builder()
                        .longitude(locationDao.getLongitude().doubleValue())
                        .latitude(locationDao.getLatitude().doubleValue())
                        .build())
                .name(locationDao.getName())
                .type(locationDao.getType())
                .uuid(locationDao.getUuid())
                .build());
    }

}

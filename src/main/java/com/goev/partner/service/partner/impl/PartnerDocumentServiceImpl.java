package com.goev.partner.service.partner.impl;

import com.goev.lib.exceptions.ResponseException;
import com.goev.partner.dao.partner.detail.PartnerDao;
import com.goev.partner.dao.partner.document.PartnerDocumentDao;
import com.goev.partner.dao.partner.document.PartnerDocumentTypeDao;
import com.goev.partner.dto.common.PageDto;
import com.goev.partner.dto.common.PaginatedResponseDto;
import com.goev.partner.dto.partner.detail.PartnerDetailsDto;
import com.goev.partner.dto.partner.document.PartnerDocumentDto;
import com.goev.partner.dto.partner.document.PartnerDocumentTypeDto;
import com.goev.partner.enums.DocumentStatus;
import com.goev.partner.repository.partner.detail.PartnerRepository;
import com.goev.partner.repository.partner.document.PartnerDocumentRepository;
import com.goev.partner.repository.partner.document.PartnerDocumentTypeRepository;
import com.goev.partner.service.partner.PartnerDocumentService;
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
public class PartnerDocumentServiceImpl implements PartnerDocumentService {
    private final PartnerRepository partnerRepository;
    private final PartnerDocumentRepository partnerDocumentRepository;
    private final PartnerDocumentTypeRepository partnerDocumentTypeRepository;
    private final S3Utils s3;
    @Override
    public List<PartnerDocumentDto> createDocument(String partnerUUID, List<PartnerDocumentDto> partnerDocuments) {
        PartnerDao partnerDao = partnerRepository.findByUUID(partnerUUID);
        if (partnerDao == null)
            throw new ResponseException("No partner found for id :" + partnerUUID);

        if (CollectionUtils.isEmpty(partnerDocuments))
            throw new ResponseException("No Document present");

        List<PartnerDocumentDto> result = new ArrayList<>();
        for (PartnerDocumentDto partnerDocumentDto : partnerDocuments) {
            PartnerDocumentDao partnerDocumentDao = new PartnerDocumentDao();

            if (partnerDocumentDto.getType() == null || partnerDocumentDto.getType().getUuid() == null)
                throw new ResponseException("Error in saving partner model: Invalid Manufacturer");
            PartnerDocumentTypeDao partnerDocumentTypeDao = partnerDocumentTypeRepository.findByUUID(partnerDocumentDto.getType().getUuid());

            if (partnerDocumentTypeDao == null || partnerDocumentTypeDao.getId() == null)
                throw new ResponseException("Error in saving partner document: Invalid Document Type");

            partnerDocumentDao.setUrl(s3.getUrlForPath(partnerDocumentDto.getUrl(), partnerDocumentTypeDao.getS3Key()));
            partnerDocumentDao.setStatus(DocumentStatus.UPLOADED.name());
            partnerDocumentDao.setDescription(partnerDocumentDto.getDescription());
            partnerDocumentDao.setFileName(partnerDocumentDto.getFileName());
            partnerDocumentDao.setPartnerId(partnerDao.getId());
            partnerDocumentDao.setPartnerDocumentTypeId(partnerDocumentTypeDao.getId());
            partnerDocumentDao = partnerDocumentRepository.save(partnerDocumentDao);
            if (partnerDocumentDao == null)
                throw new ResponseException("Error in saving partner document");
            result.add(PartnerDocumentDto.builder()
                    .uuid(partnerDocumentDto.getUuid())
                    .type(PartnerDocumentTypeDto.builder()
                            .uuid(partnerDocumentTypeDao.getUuid())
                            .label(partnerDocumentTypeDao.getLabel())
                            .name(partnerDocumentTypeDao.getName())
                            .groupKey(partnerDocumentTypeDao.getGroupKey())
                            .groupDescription(partnerDocumentTypeDao.getGroupDescription())
                            .build())
                    .fileName(partnerDocumentTypeDao.getName())
                    .description(partnerDocumentDao.getDescription())
                    .status(partnerDocumentDao.getStatus())
                    .url(partnerDocumentDao.getUrl())
                    .build());
        }
        return result;
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
}

package com.goev.partner.service.partner.impl;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.goev.lib.exceptions.ResponseException;
import com.goev.partner.dao.partner.detail.PartnerDao;
import com.goev.partner.dao.partner.document.PartnerDocumentDao;
import com.goev.partner.dao.partner.document.PartnerDocumentTypeDao;
import com.goev.partner.dto.partner.document.PartnerDocumentDto;
import com.goev.partner.dto.partner.document.PartnerDocumentTypeDto;
import com.goev.partner.repository.partner.detail.PartnerRepository;
import com.goev.partner.repository.partner.document.PartnerDocumentRepository;
import com.goev.partner.repository.partner.document.PartnerDocumentTypeRepository;
import com.goev.partner.service.partner.PartnerDocumentFileService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class PartnerDocumentFileServiceImpl implements PartnerDocumentFileService {
    private final PartnerDocumentRepository partnerDocumentRepository;
    private final PartnerRepository partnerRepository;

    private final PartnerDocumentTypeRepository partnerDocumentTypeRepository;

    public PartnerDocumentDto createPartnerFile(String partnerUUID, PartnerDocumentDto partnerDocumentDto) {

        PartnerDao partner = partnerRepository.findByUUID(partnerUUID);
        if (partner == null)
            throw new ResponseException("No partner found for Id: " + partnerUUID);

        PartnerDocumentTypeDto typeDto = partnerDocumentDto.getType();
        if (typeDto == null) {
            throw new ResponseException("Document type must be provided");
        }

        PartnerDocumentTypeDao typeDao = partnerDocumentTypeRepository.findByUUID(typeDto.getUuid());
        if (typeDao == null) {
            typeDao = new PartnerDocumentTypeDao();
            typeDao.setUuid(typeDto.getUuid());
            typeDao.setName(typeDto.getName());
            typeDao.setS3Key(typeDto.getS3Key());
            typeDao.setLabel(typeDto.getLabel());
            typeDao.setGroupKey(typeDto.getGroupKey());
            typeDao.setGroupDescription(typeDto.getGroupDescription());
            typeDao.setNeedsVerification(typeDto.getNeedsVerification());
            typeDao.setIsMandatory(typeDto.getIsMandatory());
            typeDao = partnerDocumentTypeRepository.save(typeDao);
        }

        PartnerDocumentDao partnerDocumentDao = new PartnerDocumentDao();
        partnerDocumentDao.setPartnerId(partner.getId());
        partnerDocumentDao.setFileName(partnerDocumentDto.getFileName());
        partnerDocumentDao.setDescription(partnerDocumentDto.getDescription());
        partnerDocumentDao.setStatus(partnerDocumentDto.getStatus());
        partnerDocumentDao.setUrl(partnerDocumentDto.getUrl());
        partnerDocumentDao.setUuid(partnerDocumentDto.getUuid());
        partnerDocumentDao.setPartnerDocumentTypeId(typeDao.getId());

        if (partnerDocumentDto.getData() != null) {
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                String jsonData = objectMapper.writeValueAsString(partnerDocumentDto.getData());
                partnerDocumentDao.setData(jsonData);
            } catch (Exception e) {
                throw new ResponseException(500, e.getMessage());
            }
        }

        partnerDocumentDao = partnerDocumentRepository.save(partnerDocumentDao);

        return PartnerDocumentDto.builder()
               .fileName(partnerDocumentDao.getFileName())
                .uuid(partnerDocumentDao.getUuid())
                .url(partnerDocumentDao.getUrl())
               .description(partnerDocumentDao.getDescription())
              .status(partnerDocumentDao.getStatus())
                .type(PartnerDocumentTypeDto.fromDao(typeDao))
                .data(partnerDocumentDto.getData())
                .build();
    }

}
package com.goev.partner.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.goev.lib.dto.ResponseDto;
import com.goev.lib.dto.StatusDto;
import com.goev.partner.dto.partner.document.PartnerDocumentDto;
import com.goev.partner.dto.partner.document.PartnerDocumentTypeDto;
import com.goev.partner.service.partner.PartnerDocumentFileService;
import com.goev.partner.service.partner.impl.PartnerDocumentFileServiceImpl;
import com.goev.partner.utilities.StorageUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping(value = "/api/v1/partner-management")
@AllArgsConstructor
public class FileControllerPartner {

    private final StorageUtils storageUtils;
    private final PartnerDocumentFileService partnerDocumentFileService;

    @PostMapping(value = "/document/file")
    public ResponseDto<List<PartnerDocumentDto>> uploadFiles(
            @RequestParam("file") MultipartFile file,
            @RequestParam("partnerUUID") String partnerUUID,
            @RequestParam("partnerDocument") String partnerDocumentJson) {

        List<PartnerDocumentDto> partnerDocuments = parsePartnerDocuments(partnerDocumentJson);

        String fileName = storageUtils.store(file);

        List<PartnerDocumentDto> updatedPartnerDocuments = partnerDocuments.stream().map(partnerDocument -> {
            PartnerDocumentDto partnerDocumentDto = PartnerDocumentDto.builder()
                    .uuid(UUID.randomUUID().toString())
                    .fileName(fileName)
                    .description(partnerDocument.getDescription())
                    .status(partnerDocument.getStatus())
                    .data(partnerDocument.getData())
                    .type(PartnerDocumentTypeDto.builder()
                            .uuid(UUID.randomUUID().toString())
                            .name(partnerDocument.getType().getName())
                            .s3Key(partnerDocument.getType().getS3Key())
                            .label(partnerDocument.getType().getLabel())
                            .groupKey(partnerDocument.getType().getGroupKey())
                            .groupDescription(partnerDocument.getType().getGroupDescription())
                            .isMandatory(partnerDocument.getType().getIsMandatory())
                            .needsVerification(partnerDocument.getType().getNeedsVerification())
                            .build())
                    .build();

            partnerDocumentFileService.createPartnerFile(partnerUUID, partnerDocumentDto);

            return partnerDocumentDto;
        }).toList();

        return new ResponseDto<>(StatusDto.builder().message("SUCCESS").build(), 200, updatedPartnerDocuments);
    }

    private List<PartnerDocumentDto> parsePartnerDocuments(String partnerDocumentJson) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(partnerDocumentJson, new TypeReference<List<PartnerDocumentDto>>() {});
        } catch (Exception e) {
            log.error("Error parsing partner document JSON", e);
            throw new RuntimeException("Invalid partner document data", e);
        }
    }
}


package com.goev.partner.dto.partner.detail;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.joda.deser.DateTimeDeserializer;
import com.fasterxml.jackson.datatype.joda.ser.DateTimeSerializer;
import com.goev.partner.dao.partner.document.PartnerDocumentDao;
import com.goev.partner.dao.partner.document.PartnerDocumentTypeDao;
import com.goev.partner.dto.business.BusinessClientDto;
import com.goev.partner.dto.business.BusinessSegmentDto;
import com.goev.partner.dto.location.LocationDto;
import com.goev.partner.dto.partner.PartnerViewDto;
import com.goev.partner.dto.partner.document.PartnerDocumentDto;
import com.goev.partner.dto.partner.document.PartnerDocumentTypeDto;
import com.goev.partner.dto.partner.duty.PartnerShiftDto;
import com.goev.partner.enums.DocumentStatus;
import lombok.*;
import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PartnerDetailDto {
    private PartnerViewDto partner;
    private String uuid;
    @JsonSerialize(using = DateTimeSerializer.class)
    @JsonDeserialize(using = DateTimeDeserializer.class)
    private DateTime onboardingDate;
    @JsonSerialize(using = DateTimeSerializer.class)
    @JsonDeserialize(using = DateTimeDeserializer.class)
    private DateTime deboardingDate;
    private String dlNumber;
    @JsonSerialize(using = DateTimeSerializer.class)
    @JsonDeserialize(using = DateTimeDeserializer.class)
    private DateTime joiningDate;
    @JsonSerialize(using = DateTimeSerializer.class)
    @JsonDeserialize(using = DateTimeDeserializer.class)
    private DateTime dlExpiryDate;
    private LocationDto homeLocation;
    @JsonSerialize(using = DateTimeSerializer.class)
    @JsonDeserialize(using = DateTimeDeserializer.class)
    private DateTime interviewDate;
    private String sourceOfLeadType;
    private String sourceOfLead;
    private PartnerShiftDto shiftDetails;
    private BusinessSegmentDto businessSegment;
    private BusinessClientDto businessClient;
    private String drivingTestStatus;
    private String selectionStatus;
    private String remark;
    private Boolean isVerified;
    private String email;
    private String firstName;
    private String lastName;
    private String state;
    private String fathersName;
    private String aadhaarCardNumber;
    private String localAddress;
    private String permanentAddress;
    private String profileUrl;


    public static List<PartnerDocumentDto> getPartnerDocumentDtoList(Map<Integer, PartnerDocumentTypeDao> documentTypeIdToDocumentTypeMap, Map<Integer, PartnerDocumentDao> existingDocumentMap) {
        Map<Integer, PartnerDocumentDto> finalDocumentResultMap = new HashMap<>();
        for (Map.Entry<Integer, PartnerDocumentTypeDao> entry : documentTypeIdToDocumentTypeMap.entrySet()) {
            PartnerDocumentTypeDao type = entry.getValue();
            PartnerDocumentDto partnerDocumentDto = PartnerDocumentDto.builder()
                    .type(PartnerDocumentTypeDto.builder()
                            .uuid(type.getUuid())
                            .label(type.getLabel())
                            .name(type.getName())
                            .groupKey(type.getGroupKey())
                            .groupDescription(type.getGroupDescription())
                            .fileTypes(type.getFileTypes())
                            .icon(type.getIcon())
                            .needsVerification(type.getNeedsVerification())
                            .isMandatory(type.getIsMandatory())
                            .build())
                    .status(DocumentStatus.PENDING.name())
                    .build();
            if (existingDocumentMap.containsKey(entry.getKey())) {
                PartnerDocumentDao existingDoc = existingDocumentMap.get(entry.getKey());
                partnerDocumentDto.setUrl(existingDoc.getUrl());
                partnerDocumentDto.setDescription(existingDoc.getDescription());
                partnerDocumentDto.setStatus(existingDoc.getStatus());
                partnerDocumentDto.setUuid(existingDoc.getUuid());
                partnerDocumentDto.setFileName(existingDoc.getFileName());
            }
            finalDocumentResultMap.put(entry.getKey(), partnerDocumentDto);
        }
        return new ArrayList<>(finalDocumentResultMap.values());
    }
}

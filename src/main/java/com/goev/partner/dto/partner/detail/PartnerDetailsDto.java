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
import com.goev.partner.dto.partner.attendance.PartnerShiftConfigurationDto;
import com.goev.partner.dto.partner.document.PartnerDocumentDto;
import com.goev.partner.dto.partner.document.PartnerDocumentTypeDto;
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
public class PartnerDetailsDto {
    private PartnerDto details;
    private String uuid;
    private List<PartnerDocumentDto> documents;
    private List<PartnerReferenceDto> references;
    @JsonSerialize(using = DateTimeSerializer.class)
    @JsonDeserialize(using = DateTimeDeserializer.class)
    private DateTime onboardingDate;
    @JsonSerialize(using = DateTimeSerializer.class)
    @JsonDeserialize(using = DateTimeDeserializer.class)
    private DateTime deboardingDate;
    private String dlNumber;
    private List<PartnerAccountDto> accounts;
    @JsonSerialize(using = DateTimeSerializer.class)
    @JsonDeserialize(using = DateTimeDeserializer.class)
    private DateTime joiningDate;
    @JsonSerialize(using = DateTimeSerializer.class)
    @JsonDeserialize(using = DateTimeDeserializer.class)
    private DateTime dlExpiry;
    private LocationDto homeLocation;
    @JsonSerialize(using = DateTimeSerializer.class)
    @JsonDeserialize(using = DateTimeDeserializer.class)
    private DateTime interviewDate;
    private String sourceOfLeadType;
    private String sourceOfLead;
    private PartnerShiftConfigurationDto shiftDetails;
    private BusinessSegmentDto businessSegment;
    private BusinessClientDto businessClient;
    private String driverTestStatus;
    private String selectionStatus;
    private String remark;
    private Boolean isVerified;

    public static List<PartnerDocumentDto> getPartnerDocumentDtoList(Map<Integer, PartnerDocumentTypeDao> documentTypeIdToDocumentTypeMap, Map<Integer, PartnerDocumentDao> existingDocumentMap) {
        Map<Integer, PartnerDocumentDto> finalDocumentResultMap = new HashMap<>();
        for (Map.Entry<Integer, PartnerDocumentTypeDao> entry : documentTypeIdToDocumentTypeMap.entrySet()) {
            PartnerDocumentTypeDao type = entry.getValue();
            PartnerDocumentDto partnerDocumentDto = PartnerDocumentDto.builder()
                    .type(PartnerDocumentTypeDto.builder()
                            .name(type.getName())
                            .label(type.getLabel())
                            .uuid(type.getUuid())
                            .groupKey(type.getGroupKey())
                            .groupDescription(type.getGroupDescription())
                            .build())
                    .status(DocumentStatus.PENDING.name())
                    .build();
            if (existingDocumentMap.containsKey(entry.getKey())) {
                PartnerDocumentDao existingDoc = existingDocumentMap.get(entry.getKey());
                partnerDocumentDto.setUrl(existingDoc.getUrl());
                partnerDocumentDto.setDescription(existingDoc.getDescription());
                partnerDocumentDto.setStatus(existingDoc.getStatus());
                partnerDocumentDto.setUuid(existingDoc.getUuid());
            }
            finalDocumentResultMap.put(entry.getKey(), partnerDocumentDto);
        }
        return new ArrayList<>(finalDocumentResultMap.values());
    }
}

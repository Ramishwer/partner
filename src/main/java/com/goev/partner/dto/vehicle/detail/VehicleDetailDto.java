package com.goev.partner.dto.vehicle.detail;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.joda.deser.DateTimeDeserializer;
import com.fasterxml.jackson.datatype.joda.ser.DateTimeSerializer;
import com.goev.partner.dao.vehicle.document.VehicleDocumentDao;
import com.goev.partner.dao.vehicle.document.VehicleDocumentTypeDao;
import com.goev.partner.dto.location.LocationDto;
import com.goev.partner.dto.vehicle.VehicleViewDto;
import com.goev.partner.dto.vehicle.document.VehicleDocumentDto;
import com.goev.partner.dto.vehicle.document.VehicleDocumentTypeDto;
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
public class VehicleDetailDto {
    private VehicleViewDto vehicle;
    private String uuid;
    private VehicleModelDto vehicleModel;
    private VehicleCategoryDto vehicleCategory;
    @JsonSerialize(using = DateTimeSerializer.class)
    @JsonDeserialize(using = DateTimeDeserializer.class)
    private DateTime onboardingDate;
    @JsonSerialize(using = DateTimeSerializer.class)
    @JsonDeserialize(using = DateTimeDeserializer.class)
    private DateTime deboardingDate;
    private LocationDto homeLocation;
    @JsonSerialize(using = DateTimeSerializer.class)
    @JsonDeserialize(using = DateTimeDeserializer.class)
    private DateTime insuranceExpiry;
    private String insurancePolicyNumber;
    private String vinNumber;
    private String motorNumber;
    private String batteryNumber;
    private String hasDuplicateKeys;
    @JsonSerialize(using = DateTimeSerializer.class)
    @JsonDeserialize(using = DateTimeDeserializer.class)
    private DateTime registrationDate;
    private String state;
    private String imageUrl;
    private String plateNumber;


    public static List<VehicleDocumentDto> getVehicleDocumentDtoList(Map<Integer, VehicleDocumentTypeDao> documentTypeIdToDocumentTypeMap, Map<Integer, VehicleDocumentDao> existingDocumentMap) {
        Map<Integer, VehicleDocumentDto> finalDocumentResultMap = new HashMap<>();
        for (Map.Entry<Integer, VehicleDocumentTypeDao> entry : documentTypeIdToDocumentTypeMap.entrySet()) {
            VehicleDocumentTypeDao type = entry.getValue();
            VehicleDocumentDto vehicleDocumentDto = VehicleDocumentDto.builder()
                    .type(VehicleDocumentTypeDto.builder()
                            .name(type.getName())
                            .label(type.getLabel())
                            .groupKey(type.getGroupKey())
                            .groupDescription(type.getGroupDescription())
                            .uuid(type.getUuid())
                            .build())
                    .status(DocumentStatus.PENDING.name())
                    .build();
            if (existingDocumentMap.containsKey(entry.getKey())) {
                VehicleDocumentDao existingDoc = existingDocumentMap.get(entry.getKey());
                vehicleDocumentDto.setUrl(existingDoc.getUrl());
                vehicleDocumentDto.setDescription(existingDoc.getDescription());
                vehicleDocumentDto.setStatus(existingDoc.getStatus());
                vehicleDocumentDto.setUuid(existingDoc.getUuid());
            }
            finalDocumentResultMap.put(entry.getKey(), vehicleDocumentDto);
        }
        return new ArrayList<>(finalDocumentResultMap.values());
    }
}

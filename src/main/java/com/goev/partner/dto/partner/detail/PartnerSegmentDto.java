package com.goev.partner.dto.partner.detail;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PartnerSegmentDto {
    private String name;
    private String uuid;
    private String description;

    public static PartnerSegmentDto fromDao(PartnerSegmentDto partnerSegmentDao){
        return PartnerSegmentDto.builder()
                .uuid(partnerSegmentDao.getUuid())
                .description(partnerSegmentDao.getDescription())
                .name(partnerSegmentDao.getName())
                .build();
    }
}


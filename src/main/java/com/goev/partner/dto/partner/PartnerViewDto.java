package com.goev.partner.dto.partner;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.joda.deser.DateTimeDeserializer;
import com.fasterxml.jackson.datatype.joda.ser.DateTimeSerializer;
import com.goev.partner.constant.ApplicationConstants;
import com.goev.partner.dao.partner.detail.PartnerDao;
import com.goev.partner.dto.location.LocationDto;
import lombok.*;
import org.joda.time.DateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class PartnerViewDto {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String punchId;
    @JsonSerialize(using = DateTimeSerializer.class)
    @JsonDeserialize(using = DateTimeDeserializer.class)
    private DateTime onboardingDate;
    private String uuid;
    private String onboardingStatus;
    private String profileUrl;
    private LocationDto homeLocation;

    public static PartnerViewDto fromDao(PartnerDao partnerDao) {
        if (partnerDao.getViewInfo() == null)
            return null;
        PartnerViewDto result = ApplicationConstants.GSON.fromJson(partnerDao.getViewInfo(), PartnerViewDto.class);
        result.setUuid(partnerDao.getUuid());
        return result;
    }
}

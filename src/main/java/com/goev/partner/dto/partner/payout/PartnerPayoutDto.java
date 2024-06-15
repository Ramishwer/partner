package com.goev.partner.dto.partner.payout;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.joda.deser.DateTimeDeserializer;
import com.fasterxml.jackson.datatype.joda.ser.DateTimeSerializer;
import com.goev.partner.constant.ApplicationConstants;
import com.goev.partner.dao.partner.payout.PartnerPayoutDao;
import com.goev.partner.dto.partner.PartnerViewDto;
import lombok.*;
import org.joda.time.DateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PartnerPayoutDto {

    private String uuid;
    private PartnerViewDto partner;
    @JsonSerialize(using = DateTimeSerializer.class)
    @JsonDeserialize(using = DateTimeDeserializer.class)
    private DateTime payoutStartDate;
    @JsonSerialize(using = DateTimeSerializer.class)
    @JsonDeserialize(using = DateTimeDeserializer.class)
    private DateTime payoutEndDate;
    private String status;
    @JsonSerialize(using = DateTimeSerializer.class)
    @JsonDeserialize(using = DateTimeDeserializer.class)
    private DateTime finalizationDate;
    private PartnerPayoutSummaryDto payoutSummary;
    private Integer payoutTotalBookingAmount;
    private Integer payoutTotalAmount;
    private Integer payoutTotalDeductionAmount;
    private Integer payoutTotalCreditAmount;
    private Integer payoutTotalDebitAmount;


    public static PartnerPayoutDto fromDao(PartnerPayoutDao partnerPayoutDao,PartnerViewDto partnerViewDto){
        return PartnerPayoutDto.builder()
                .partner(partnerViewDto)
                .uuid(partnerPayoutDao.getUuid())
                .payoutStartDate(partnerPayoutDao.getPayoutStartDate())
                .payoutEndDate(partnerPayoutDao.getPayoutEndDate())
                .status(partnerPayoutDao.getStatus())
                .finalizationDate(partnerPayoutDao.getPayoutEndDate())
                .payoutSummary(ApplicationConstants.GSON.fromJson(partnerPayoutDao.getPayoutSummary(),PartnerPayoutSummaryDto.class))
                .payoutTotalBookingAmount(partnerPayoutDao.getPayoutTotalBookingAmount())
                .payoutTotalAmount(partnerPayoutDao.getPayoutTotalAmount())
                .payoutTotalDeductionAmount(partnerPayoutDao.getPayoutTotalDeductionAmount())
                .payoutTotalCreditAmount(partnerPayoutDao.getPayoutTotalCreditAmount())
                .payoutTotalDebitAmount(partnerPayoutDao.getPayoutTotalDebitAmount())
                .build();
    }

}

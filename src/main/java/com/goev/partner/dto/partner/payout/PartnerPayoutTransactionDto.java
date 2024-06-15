package com.goev.partner.dto.partner.payout;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.joda.deser.DateTimeDeserializer;
import com.fasterxml.jackson.datatype.joda.ser.DateTimeSerializer;
import com.goev.partner.constant.ApplicationConstants;
import com.goev.partner.dao.partner.payout.PartnerPayoutDao;
import com.goev.partner.dao.partner.payout.PartnerPayoutTransactionDao;
import com.goev.partner.dto.partner.PartnerViewDto;
import com.goev.partner.dto.payout.PayoutElementDto;
import com.google.gson.reflect.TypeToken;
import lombok.*;
import org.joda.time.DateTime;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PartnerPayoutTransactionDto {

    private String uuid;
    private String date;
    private String day;
    private String amount;
    private String title;
    private String subtitle;
    private String message;
    @JsonSerialize(using = DateTimeSerializer.class)
    @JsonDeserialize(using = DateTimeDeserializer.class)
    private DateTime transactionTime;
    private String transactionType;
    private List<PayoutElementDto> calculatedPayoutElements;
    private String status;


    public static PartnerPayoutTransactionDto fromDao(PartnerPayoutTransactionDao transactionDao) {
        return PartnerPayoutTransactionDto.builder()
                .uuid(transactionDao.getUuid())
                .date(transactionDao.getDate())
                .day(transactionDao.getDay())
                .amount(transactionDao.getAmount())
                .title(transactionDao.getTitle())
                .subtitle(transactionDao.getSubtitle())
                .message(transactionDao.getMessage())
                .transactionTime(transactionDao.getTransactionTime())
                .transactionType(transactionDao.getTransactionType())
                .calculatedPayoutElements(ApplicationConstants.GSON.fromJson(transactionDao.getCalculatedPayoutElements(),new TypeToken<List<PayoutElementDto>>(){}.getType()))
                .status(transactionDao.getStatus())
                .build();


    }
}

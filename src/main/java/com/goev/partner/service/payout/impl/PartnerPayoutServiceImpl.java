package com.goev.partner.service.payout.impl;

import com.goev.lib.exceptions.ResponseException;
import com.goev.partner.constant.ApplicationConstants;
import com.goev.partner.dao.partner.detail.PartnerDao;
import com.goev.partner.dao.partner.payout.PartnerPayoutDao;
import com.goev.partner.dao.partner.payout.PartnerPayoutTransactionDao;
import com.goev.partner.dto.common.PageDto;
import com.goev.partner.dto.common.PaginatedResponseDto;
import com.goev.partner.dto.partner.PartnerViewDto;
import com.goev.partner.dto.partner.payout.PartnerPayoutDto;
import com.goev.partner.dto.partner.payout.PartnerPayoutSummaryDto;
import com.goev.partner.dto.partner.payout.PartnerPayoutTransactionDto;
import com.goev.partner.repository.partner.detail.PartnerRepository;
import com.goev.partner.repository.partner.payout.PartnerPayoutRepository;
import com.goev.partner.repository.partner.payout.PartnerPayoutTransactionRepository;
import com.goev.partner.service.payout.PartnerPayoutService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class PartnerPayoutServiceImpl implements PartnerPayoutService {

    private final PartnerRepository partnerRepository;
    private final PartnerPayoutRepository partnerPayoutRepository;
    private final PartnerPayoutTransactionRepository partnerPayoutTransactionRepository;

    @Override
    public PaginatedResponseDto<PartnerPayoutDto> getPayouts(String partnerUUID, PageDto page) {
        PartnerDao partnerDao = partnerRepository.findByUUID(partnerUUID);
        if (partnerDao == null)
            throw new ResponseException("No partner found for id :" + partnerUUID);

        PaginatedResponseDto<PartnerPayoutDto> result = PaginatedResponseDto.<PartnerPayoutDto>builder().elements(new ArrayList<>()).build();
        List<PartnerPayoutDao> payouts = partnerPayoutRepository.findAllByPartnerId(partnerDao.getId(), page);
        if (CollectionUtils.isEmpty(payouts))
            return result;
        for (PartnerPayoutDao payout : payouts) {
            PartnerPayoutDto partnerPayoutDto = PartnerPayoutDto.fromDao(payout, PartnerViewDto.fromDao(partnerDao));
            if (partnerPayoutDto == null)
                continue;
            result.getElements().add(partnerPayoutDto);
        }
        return result;
    }

    @Override
    public PartnerPayoutSummaryDto getPayoutSummaryForPayoutUUID(String partnerUUID, String partnerPayoutUUID) {
        PartnerDao partnerDao = partnerRepository.findByUUID(partnerUUID);
        if (partnerDao == null)
            throw new ResponseException("No partner found for id :" + partnerUUID);

        PartnerPayoutDao payoutDao = partnerPayoutRepository.findByUUID(partnerPayoutUUID);
        if (payoutDao == null)
            throw new ResponseException("No payout found for id :" + partnerPayoutUUID);
        return ApplicationConstants.GSON.fromJson(payoutDao.getPayoutSummary(), PartnerPayoutSummaryDto.class);
    }

    @Override
    public PaginatedResponseDto<PartnerPayoutTransactionDto> getPayoutTransactionsForPayoutUUID(String partnerUUID, String partnerPayoutUUID, PageDto page) {
        PartnerDao partnerDao = partnerRepository.findByUUID(partnerUUID);
        if (partnerDao == null)
            throw new ResponseException("No partner found for id :" + partnerUUID);

        PartnerPayoutDao payoutDao = partnerPayoutRepository.findByUUID(partnerPayoutUUID);
        if (payoutDao == null)
            throw new ResponseException("No payout found for id :" + partnerPayoutUUID);

        PaginatedResponseDto<PartnerPayoutTransactionDto> result = PaginatedResponseDto.<PartnerPayoutTransactionDto>builder().elements(new ArrayList<>()).build();
        List<PartnerPayoutTransactionDao> payoutTransactions = partnerPayoutTransactionRepository.findAllByPartnerPayoutId(payoutDao.getId(), page);
        if (CollectionUtils.isEmpty(payoutTransactions))
            return result;
        for (PartnerPayoutTransactionDao transactionDao : payoutTransactions) {
            PartnerPayoutTransactionDto transactionDto = PartnerPayoutTransactionDto.fromDao(transactionDao);
            if (transactionDto == null)
                continue;
            result.getElements().add(transactionDto);
        }
        return result;
    }


}

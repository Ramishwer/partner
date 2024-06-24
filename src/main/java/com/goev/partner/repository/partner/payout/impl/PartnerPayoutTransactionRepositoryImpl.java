package com.goev.partner.repository.partner.payout.impl;

import com.goev.lib.enums.RecordState;
import com.goev.partner.dao.partner.payout.PartnerPayoutTransactionDao;
import com.goev.partner.dto.common.PageDto;
import com.goev.partner.repository.partner.payout.PartnerPayoutTransactionRepository;
import com.goev.record.partner.tables.records.PartnerPayoutTransactionsRecord;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.goev.record.partner.tables.PartnerPayoutTransactions.PARTNER_PAYOUT_TRANSACTIONS;

@Slf4j
@Repository
@AllArgsConstructor
public class PartnerPayoutTransactionRepositoryImpl implements PartnerPayoutTransactionRepository {

    private final DSLContext context;

    @Override
    public PartnerPayoutTransactionDao save(PartnerPayoutTransactionDao partnerPayoutTransaction) {
        PartnerPayoutTransactionsRecord partnerPayoutTransactionsRecord = context.newRecord(PARTNER_PAYOUT_TRANSACTIONS, partnerPayoutTransaction);
        partnerPayoutTransactionsRecord.store();
        partnerPayoutTransaction.setId(partnerPayoutTransactionsRecord.getId());
        partnerPayoutTransaction.setUuid(partnerPayoutTransactionsRecord.getUuid());
        partnerPayoutTransaction.setCreatedBy(partnerPayoutTransactionsRecord.getCreatedBy());
        partnerPayoutTransaction.setUpdatedBy(partnerPayoutTransactionsRecord.getUpdatedBy());
        partnerPayoutTransaction.setCreatedOn(partnerPayoutTransactionsRecord.getCreatedOn());
        partnerPayoutTransaction.setUpdatedOn(partnerPayoutTransactionsRecord.getUpdatedOn());
        partnerPayoutTransaction.setIsActive(partnerPayoutTransactionsRecord.getIsActive());
        partnerPayoutTransaction.setState(partnerPayoutTransactionsRecord.getState());
        partnerPayoutTransaction.setApiSource(partnerPayoutTransactionsRecord.getApiSource());
        partnerPayoutTransaction.setNotes(partnerPayoutTransactionsRecord.getNotes());
        return partnerPayoutTransaction;
    }

    @Override
    public PartnerPayoutTransactionDao update(PartnerPayoutTransactionDao partnerPayoutTransaction) {
        PartnerPayoutTransactionsRecord partnerPayoutTransactionsRecord = context.newRecord(PARTNER_PAYOUT_TRANSACTIONS, partnerPayoutTransaction);
        partnerPayoutTransactionsRecord.update();


        partnerPayoutTransaction.setCreatedBy(partnerPayoutTransactionsRecord.getCreatedBy());
        partnerPayoutTransaction.setUpdatedBy(partnerPayoutTransactionsRecord.getUpdatedBy());
        partnerPayoutTransaction.setCreatedOn(partnerPayoutTransactionsRecord.getCreatedOn());
        partnerPayoutTransaction.setUpdatedOn(partnerPayoutTransactionsRecord.getUpdatedOn());
        partnerPayoutTransaction.setIsActive(partnerPayoutTransactionsRecord.getIsActive());
        partnerPayoutTransaction.setState(partnerPayoutTransactionsRecord.getState());
        partnerPayoutTransaction.setApiSource(partnerPayoutTransactionsRecord.getApiSource());
        partnerPayoutTransaction.setNotes(partnerPayoutTransactionsRecord.getNotes());
        return partnerPayoutTransaction;
    }

    @Override
    public PartnerPayoutTransactionDao findByUUID(String uuid) {
        return context.selectFrom(PARTNER_PAYOUT_TRANSACTIONS)
                .where(PARTNER_PAYOUT_TRANSACTIONS.UUID.eq(uuid))
                .and(PARTNER_PAYOUT_TRANSACTIONS.IS_ACTIVE.eq(true))
                .fetchAnyInto(PartnerPayoutTransactionDao.class);
    }

    @Override
    public PartnerPayoutTransactionDao findById(Integer id) {
        return context.selectFrom(PARTNER_PAYOUT_TRANSACTIONS)
                .where(PARTNER_PAYOUT_TRANSACTIONS.ID.eq(id))
                .and(PARTNER_PAYOUT_TRANSACTIONS.IS_ACTIVE.eq(true))
                .fetchAnyInto(PartnerPayoutTransactionDao.class);
    }

    @Override
    public List<PartnerPayoutTransactionDao> findAllByIds(List<Integer> ids) {
        return context.selectFrom(PARTNER_PAYOUT_TRANSACTIONS)
                .where(PARTNER_PAYOUT_TRANSACTIONS.ID.in(ids))
                .and(PARTNER_PAYOUT_TRANSACTIONS.IS_ACTIVE.eq(true))
                .fetchInto(PartnerPayoutTransactionDao.class);
    }


    @Override
    public List<PartnerPayoutTransactionDao> findAllByPartnerPayoutId(Integer id, PageDto page) {
        return context.selectFrom(PARTNER_PAYOUT_TRANSACTIONS)
                .where(PARTNER_PAYOUT_TRANSACTIONS.PARTNER_PAYOUT_ID.eq(id))
                .and(PARTNER_PAYOUT_TRANSACTIONS.STATE.eq(RecordState.ACTIVE.name()))
                .and(PARTNER_PAYOUT_TRANSACTIONS.IS_ACTIVE.eq(true))
                .offset(page.getStart())
                .limit(page.getLimit())
                .fetchInto(PartnerPayoutTransactionDao.class);
    }


}

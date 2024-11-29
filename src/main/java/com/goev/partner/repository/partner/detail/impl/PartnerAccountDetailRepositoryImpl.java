package com.goev.partner.repository.partner.detail.impl;

import com.goev.partner.dao.partner.detail.PartnerAccountDetailDao;
import com.goev.partner.repository.partner.detail.PartnerAccountDetailRepository;
import com.goev.record.partner.tables.records.PartnerAccountDetailsRecord;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import static com.goev.record.partner.tables.PartnerAccountDetails.PARTNER_ACCOUNT_DETAILS;

@Slf4j
@Repository
@AllArgsConstructor
public class PartnerAccountDetailRepositoryImpl implements PartnerAccountDetailRepository {


    private final DSLContext context;

    @Override
    public PartnerAccountDetailDao save(PartnerAccountDetailDao accountDetail) {
        PartnerAccountDetailsRecord partnerAccountDetailsRecord = context.newRecord(PARTNER_ACCOUNT_DETAILS, accountDetail);
        partnerAccountDetailsRecord.store();
        accountDetail.setId(partnerAccountDetailsRecord.getId());
        accountDetail.setUuid(partnerAccountDetailsRecord.getUuid());
        accountDetail.setCreatedBy(partnerAccountDetailsRecord.getCreatedBy());
        accountDetail.setUpdatedBy(partnerAccountDetailsRecord.getUpdatedBy());
        accountDetail.setCreatedOn(partnerAccountDetailsRecord.getCreatedOn());
        accountDetail.setUpdatedOn(partnerAccountDetailsRecord.getUpdatedOn());
        accountDetail.setIsActive(partnerAccountDetailsRecord.getIsActive());
        accountDetail.setState(partnerAccountDetailsRecord.getState());
        accountDetail.setApiSource(partnerAccountDetailsRecord.getApiSource());
        accountDetail.setNotes(partnerAccountDetailsRecord.getNotes());
        return accountDetail;
    }
}
package com.goev.partner.repository.partner.payout;

import com.goev.partner.dao.partner.payout.PartnerPayoutTransactionDao;
import com.goev.partner.dto.common.PageDto;

import java.util.List;

public interface PartnerPayoutTransactionRepository {
    PartnerPayoutTransactionDao save(PartnerPayoutTransactionDao partner);
    PartnerPayoutTransactionDao update(PartnerPayoutTransactionDao partner);

    PartnerPayoutTransactionDao findByUUID(String uuid);
    PartnerPayoutTransactionDao findById(Integer id);
    List<PartnerPayoutTransactionDao> findAllByIds(List<Integer> ids);

    List<PartnerPayoutTransactionDao> findAllByPartnerPayoutId(Integer id,PageDto page);

}
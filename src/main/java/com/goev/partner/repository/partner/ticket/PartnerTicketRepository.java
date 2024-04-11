package com.goev.partner.repository.partner.ticket;

import com.goev.partner.dao.partner.ticket.PartnerTicketDao;

import java.util.List;

public interface PartnerTicketRepository {
    PartnerTicketDao save(PartnerTicketDao partner);
    PartnerTicketDao update(PartnerTicketDao partner);
    void delete(Integer id);
    PartnerTicketDao findByUUID(String uuid);
    PartnerTicketDao findById(Integer id);
    List<PartnerTicketDao> findAllByIds(List<Integer> ids);
    List<PartnerTicketDao> findAll();
}
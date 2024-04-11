package com.goev.partner.repository.vehicle.ticket;

import com.goev.partner.dao.vehicle.ticket.VehicleTicketDao;

import java.util.List;

public interface VehicleTicketRepository {
    VehicleTicketDao save(VehicleTicketDao partner);
    VehicleTicketDao update(VehicleTicketDao partner);
    void delete(Integer id);
    VehicleTicketDao findByUUID(String uuid);
    VehicleTicketDao findById(Integer id);
    List<VehicleTicketDao> findAllByIds(List<Integer> ids);
    List<VehicleTicketDao> findAll();
}

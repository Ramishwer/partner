package com.goev.partner.utilities;

import com.goev.lib.event.service.EventProcessor;
import com.goev.partner.config.SpringContext;
import com.goev.partner.dao.partner.detail.PartnerDao;
import com.goev.partner.dao.partner.detail.PartnerDetailDao;
import com.goev.partner.dao.partner.document.PartnerDocumentDao;
import com.goev.partner.dao.vehicle.transfer.VehicleTransferDetailDao;
import com.goev.partner.event.events.partner.save.PartnerDocumentSaveEvent;
import com.goev.partner.event.events.partner.update.PartnerDetailUpdateEvent;
import com.goev.partner.event.events.partner.update.PartnerDocumentUpdateEvent;
import com.goev.partner.event.events.partner.update.PartnerUpdateEvent;
import com.goev.partner.event.events.vehicle.save.VehicleTransferDetailSaveEvent;
import com.goev.partner.event.events.vehicle.update.VehicleTransferDetailUpdateEvent;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class EventExecutorUtils {

    public boolean fireEvent(String event, Object data) {

        switch (event) {
            case "PartnerUpdateEvent" -> {
                PartnerUpdateEvent partnerUpdateEvent = new PartnerUpdateEvent();
                partnerUpdateEvent.setData((PartnerDao) data);
                partnerUpdateEvent.setExecutionTime(DateTime.now().getMillis());
                SpringContext.getBean(EventProcessor.class).sendEvent(partnerUpdateEvent);
                return true;
            }
            case "PartnerDetailUpdateEvent" -> {
                PartnerDetailUpdateEvent partnerDetailUpdateEvent = new PartnerDetailUpdateEvent();
                partnerDetailUpdateEvent.setData((PartnerDetailDao) data);
                partnerDetailUpdateEvent.setExecutionTime(DateTime.now().getMillis());
                SpringContext.getBean(EventProcessor.class).sendEvent(partnerDetailUpdateEvent);
                return true;
            }
            case "VehicleTransferDetailSaveEvent" -> {
                VehicleTransferDetailSaveEvent vehicleTransferDetailSaveEvent = new VehicleTransferDetailSaveEvent();
                vehicleTransferDetailSaveEvent.setData((VehicleTransferDetailDao) data);
                vehicleTransferDetailSaveEvent.setExecutionTime(DateTime.now().getMillis());
                SpringContext.getBean(EventProcessor.class).sendEvent(vehicleTransferDetailSaveEvent);
                return true;
            }
            case "VehicleTransferDetailUpdateEvent" -> {
                VehicleTransferDetailUpdateEvent vehicleTransferDetailUpdateEvent = new VehicleTransferDetailUpdateEvent();
                vehicleTransferDetailUpdateEvent.setData((VehicleTransferDetailDao) data);
                vehicleTransferDetailUpdateEvent.setExecutionTime(DateTime.now().getMillis());
                SpringContext.getBean(EventProcessor.class).sendEvent(vehicleTransferDetailUpdateEvent);
                return true;
            }
            case "PartnerDocumentSaveEvent" -> {
                PartnerDocumentSaveEvent partnerDocumentSaveEvent = new PartnerDocumentSaveEvent();
                partnerDocumentSaveEvent.setData((PartnerDocumentDao) data);
                partnerDocumentSaveEvent.setExecutionTime(DateTime.now().getMillis());
                SpringContext.getBean(EventProcessor.class).sendEvent(partnerDocumentSaveEvent);
                return true;
            }
            case "PartnerDocumentUpdateEvent" -> {
                PartnerDocumentUpdateEvent partnerDocumentUpdateEvent = new PartnerDocumentUpdateEvent();
                partnerDocumentUpdateEvent.setData((PartnerDocumentDao) data);
                partnerDocumentUpdateEvent.setExecutionTime(DateTime.now().getMillis());
                SpringContext.getBean(EventProcessor.class).sendEvent(partnerDocumentUpdateEvent);
                return true;
            }

            default -> {
                return false;
            }
        }

    }
}

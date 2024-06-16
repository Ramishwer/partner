package com.goev.partner.utilities;

import com.goev.lib.event.core.Event;
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
                return fireEvent(SpringContext.getBean(PartnerUpdateEvent.class),(PartnerDao) data);
            }
            case "PartnerDetailUpdateEvent" -> {
                return fireEvent(SpringContext.getBean(PartnerDetailUpdateEvent.class),(PartnerDetailDao) data);
            }
            case "VehicleTransferDetailSaveEvent" -> {
                return fireEvent(SpringContext.getBean(VehicleTransferDetailSaveEvent.class),(VehicleTransferDetailDao) data);
            }
            case "VehicleTransferDetailUpdateEvent" -> {
                return fireEvent(SpringContext.getBean(VehicleTransferDetailUpdateEvent.class),(VehicleTransferDetailDao) data);
            }
            case "PartnerDocumentSaveEvent" -> {
                return fireEvent(SpringContext.getBean(PartnerDocumentSaveEvent.class),(PartnerDocumentDao) data);
            }
            case "PartnerDocumentUpdateEvent" -> {
                return fireEvent(SpringContext.getBean(PartnerDocumentUpdateEvent.class),(PartnerDocumentDao) data);
            }

            default -> {
                return false;
            }
        }

    }


    private static  <T>  boolean fireEvent(Event<T> eventObj, T data) {
        eventObj.setData(data);
        eventObj.setExecutionTime(DateTime.now().getMillis());
        SpringContext.getBean(EventProcessor.class).sendEvent(eventObj);
        return true;
    }
}

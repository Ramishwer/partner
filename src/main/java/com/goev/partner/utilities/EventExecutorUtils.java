package com.goev.partner.utilities;

import com.goev.lib.event.core.Event;
import com.goev.lib.event.service.EventProcessor;
import com.goev.lib.utilities.ApplicationContext;
import com.goev.partner.config.SpringContext;
import com.goev.partner.dao.booking.BookingDao;
import com.goev.partner.dao.partner.detail.PartnerDao;
import com.goev.partner.dao.partner.detail.PartnerDetailDao;
import com.goev.partner.dao.partner.document.PartnerDocumentDao;
import com.goev.partner.dao.partner.duty.PartnerDutyDao;
import com.goev.partner.dao.vehicle.transfer.VehicleTransferDetailDao;
import com.goev.partner.event.events.booking.BookingUpdateEvent;
import com.goev.partner.event.events.partner.PartnerOnboardingStatusCheckEvent;
import com.goev.partner.event.events.partner.save.PartnerDocumentSaveEvent;
import com.goev.partner.event.events.partner.save.PartnerDutySaveEvent;
import com.goev.partner.event.events.partner.update.PartnerDetailUpdateEvent;
import com.goev.partner.event.events.partner.update.PartnerDocumentUpdateEvent;
import com.goev.partner.event.events.partner.update.PartnerDutyUpdateEvent;
import com.goev.partner.event.events.partner.update.PartnerUpdateEvent;
import com.goev.partner.event.events.vehicle.save.VehicleTransferDetailSaveEvent;
import com.goev.partner.event.events.vehicle.update.VehicleTransferDetailUpdateEvent;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;

@Slf4j
@Service
@AllArgsConstructor
public class EventExecutorUtils {
    private final ExecutorService executorService;

    public boolean fireEvent(String event, Object data) {
        return fireEvent(event, data, DateTime.now());
    }

    private <T> boolean fireEvent(Event<T> eventObj, T data, DateTime executionTime) {
        eventObj.setData(data);
        eventObj.setExecutionTime(executionTime.getMillis());
        eventObj.setActionByUUID(ApplicationContext.getAuthUUID());
        eventObj.setActionByUUID(ApplicationContext.getAuthUUID());
        if (executionTime.isBefore(DateTime.now().plus(1000L))) {
            executorService.submit(() -> SpringContext.getBean(EventProcessor.class).sendEvent(eventObj));
        } else {
            executorService.submit(() -> SpringContext.getBean(EventProcessor.class).scheduleEvent(eventObj));
        }
        return true;
    }

    public boolean fireEvent(String event, Object data, DateTime executionTime) {

        switch (event) {
            case "PartnerUpdateEvent" -> {
                return fireEvent(SpringContext.getBean(PartnerUpdateEvent.class), (PartnerDao) data, executionTime);
            }
            case "PartnerDetailUpdateEvent" -> {
                return fireEvent(SpringContext.getBean(PartnerDetailUpdateEvent.class), (PartnerDetailDao) data, executionTime);
            }
            case "PartnerDutySaveEvent" -> {
                return fireEvent(SpringContext.getBean(PartnerDutySaveEvent.class), (PartnerDutyDao) data, executionTime);
            }
            case "PartnerDutyUpdateEvent" -> {
                return fireEvent(SpringContext.getBean(PartnerDutyUpdateEvent.class), (PartnerDutyDao) data, executionTime);
            }
            case "PartnerOnboardingStatusCheckEvent" -> {
                return fireEvent(SpringContext.getBean(PartnerOnboardingStatusCheckEvent.class), (String) data, executionTime);
            }
            case "VehicleTransferDetailSaveEvent" -> {
                return fireEvent(SpringContext.getBean(VehicleTransferDetailSaveEvent.class), (VehicleTransferDetailDao) data, executionTime);
            }
            case "VehicleTransferDetailUpdateEvent" -> {
                return fireEvent(SpringContext.getBean(VehicleTransferDetailUpdateEvent.class), (VehicleTransferDetailDao) data, executionTime);
            }
            case "PartnerDocumentSaveEvent" -> {
                return fireEvent(SpringContext.getBean(PartnerDocumentSaveEvent.class), (PartnerDocumentDao) data, executionTime);
            }
            case "PartnerDocumentUpdateEvent" -> {
                return fireEvent(SpringContext.getBean(PartnerDocumentUpdateEvent.class), (PartnerDocumentDao) data, executionTime);
            }
            case "BookingUpdateEvent" -> {
                return fireEvent(SpringContext.getBean(BookingUpdateEvent.class), (BookingDao) data, executionTime);
            }

            default -> {
                return false;
            }
        }

    }

}

package com.goev.partner.config;

import com.goev.lib.event.service.EventProcessor;
import com.goev.partner.event.ExtendedEventProcessor;
import com.goev.partner.event.events.asset.save.AssetSaveEvent;
import com.goev.partner.event.events.asset.save.AssetTypeSaveEvent;
import com.goev.partner.event.events.asset.update.AssetTypeUpdateEvent;
import com.goev.partner.event.events.asset.update.AssetUpdateEvent;
import com.goev.partner.event.events.booking.BookingSaveEvent;
import com.goev.partner.event.events.booking.BookingUpdateEvent;
import com.goev.partner.event.events.location.save.LocationSaveEvent;
import com.goev.partner.event.events.location.update.LocationUpdateEvent;
import com.goev.partner.event.events.partner.PartnerOnboardingStatusCheckEvent;
import com.goev.partner.event.events.partner.save.*;
import com.goev.partner.event.events.partner.update.*;
import com.goev.partner.event.events.vehicle.save.*;
import com.goev.partner.event.events.vehicle.update.*;
import com.goev.partner.event.handlers.asset.save.AssetSaveEventHandler;
import com.goev.partner.event.handlers.asset.save.AssetTypeSaveEventHandler;
import com.goev.partner.event.handlers.asset.update.AssetTypeUpdateEventHandler;
import com.goev.partner.event.handlers.asset.update.AssetUpdateEventHandler;
import com.goev.partner.event.handlers.booking.save.BookingSaveEventHandler;
import com.goev.partner.event.handlers.booking.update.BookingUpdateEventHandler;
import com.goev.partner.event.handlers.location.save.LocationSaveEventHandler;
import com.goev.partner.event.handlers.location.update.LocationUpdateEventHandler;
import com.goev.partner.event.handlers.partner.save.*;
import com.goev.partner.event.handlers.partner.update.*;
import com.goev.partner.event.handlers.vehicle.save.*;
import com.goev.partner.event.handlers.vehicle.update.*;
import com.goev.partner.event.targets.CentralTarget;
import com.goev.partner.event.targets.CustomerTarget;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
@Slf4j
public class EventConfig {

    @Bean
    public EventProcessor getEventProcessor(
            AssetUpdateEventHandler assetUpdateEventHandler,
            AssetTypeUpdateEventHandler assetTypeUpdateEventHandler,
            PartnerUpdateEventHandler partnerUpdateEventHandler,
            PartnerDetailUpdateEventHandler partnerDetailUpdateEventHandler,
            PartnerShiftUpdateEventHandler partnerShiftUpdateEventHandler,
            PartnerDutyUpdateEventHandler partnerDutyUpdateEventHandler,
            PartnerPayoutUpdateEventHandler partnerPayoutUpdateEventHandler,
            PartnerDocumentUpdateEventHandler partnerDocumentUpdateEventHandler,
            PartnerDocumentTypeUpdateEventHandler partnerDocumentTypeUpdateEventHandler,
            VehicleUpdateEventHandler vehicleUpdateEventHandler,
            VehicleDocumentUpdateEventHandler vehicleDocumentUpdateEventHandler,
            VehicleDocumentTypeUpdateEventHandler vehicleDocumentTypeUpdateEventHandler,
            VehicleDetailUpdateEventHandler vehicleDetailUpdateEventHandler,
            VehicleTransferDetailUpdateEventHandler vehicleTransferDetailUpdateEventHandler,
            VehicleAssetTransferDetailUpdateEventHandler vehicleAssetTransferDetailUpdateEventHandler,
            VehicleAssetMappingUpdateEventHandler vehicleAssetMappingUpdateEventHandler,
            BookingUpdateEventHandler bookingUpdateEventHandler,
            LocationUpdateEventHandler locationUpdateEventHandler,


            AssetSaveEventHandler assetSaveEventHandler,
            AssetTypeSaveEventHandler assetTypeSaveEventHandler,
            PartnerSaveEventHandler partnerSaveEventHandler,
            PartnerDetailSaveEventHandler partnerDetailSaveEventHandler,
            PartnerDutySaveEventHandler partnerDutySaveEventHandler,
            PartnerShiftSaveEventHandler partnerShiftSaveEventHandler,
            PartnerPayoutSaveEventHandler partnerPayoutSaveEventHandler,
            PartnerDocumentSaveEventHandler partnerDocumentSaveEventHandler,
            PartnerDocumentTypeSaveEventHandler partnerDocumentTypeSaveEventHandler,
            VehicleSaveEventHandler vehicleSaveEventHandler,
            VehicleDocumentSaveEventHandler vehicleDocumentSaveEventHandler,
            VehicleDocumentTypeSaveEventHandler vehicleDocumentTypeSaveEventHandler,
            VehicleDetailSaveEventHandler vehicleDetailSaveEventHandler,
            VehicleTransferDetailSaveEventHandler vehicleTransferDetailSaveEventHandler,
            VehicleAssetTransferDetailSaveEventHandler vehicleAssetTransferDetailSaveEventHandler,
            VehicleAssetMappingSaveEventHandler vehicleAssetMappingSaveEventHandler,
            BookingSaveEventHandler bookingSaveEventHandler,
            LocationSaveEventHandler locationSaveEventHandler


    ) {
        ExtendedEventProcessor eventProcessor = new ExtendedEventProcessor();

        eventProcessor.registerEvents(new PartnerOnboardingStatusCheckEvent());


        eventProcessor.registerEvents(new AssetUpdateEvent());
        eventProcessor.registerEvents(new AssetTypeUpdateEvent());
        eventProcessor.registerEvents(new PartnerUpdateEvent());
        eventProcessor.registerEvents(new PartnerDetailUpdateEvent());
        eventProcessor.registerEvents(new PartnerDutyUpdateEvent());
        eventProcessor.registerEvents(new PartnerShiftUpdateEvent());
        eventProcessor.registerEvents(new PartnerPayoutUpdateEvent());
        eventProcessor.registerEvents(new PartnerDocumentUpdateEvent());
        eventProcessor.registerEvents(new PartnerDocumentTypeUpdateEvent());
        eventProcessor.registerEvents(new VehicleUpdateEvent());
        eventProcessor.registerEvents(new VehicleDocumentUpdateEvent());
        eventProcessor.registerEvents(new VehicleDocumentTypeUpdateEvent());
        eventProcessor.registerEvents(new VehicleDetailUpdateEvent());
        eventProcessor.registerEvents(new VehicleTransferDetailUpdateEvent());
        eventProcessor.registerEvents(new VehicleAssetTransferDetailUpdateEvent());
        eventProcessor.registerEvents(new VehicleAssetMappingUpdateEvent());
        eventProcessor.registerEvents(new BookingUpdateEvent());
        eventProcessor.registerEvents(new LocationUpdateEvent());


        eventProcessor.registerEventHandlers(new AssetUpdateEvent(), assetUpdateEventHandler);
        eventProcessor.registerEventHandlers(new AssetTypeUpdateEvent(), assetTypeUpdateEventHandler);
        eventProcessor.registerEventHandlers(new PartnerUpdateEvent(), partnerUpdateEventHandler);
        eventProcessor.registerEventHandlers(new PartnerDetailUpdateEvent(), partnerDetailUpdateEventHandler);
        eventProcessor.registerEventHandlers(new PartnerDutyUpdateEvent(), partnerDutyUpdateEventHandler);
        eventProcessor.registerEventHandlers(new PartnerShiftUpdateEvent(), partnerShiftUpdateEventHandler);
        eventProcessor.registerEventHandlers(new PartnerPayoutUpdateEvent(), partnerPayoutUpdateEventHandler);
        eventProcessor.registerEventHandlers(new PartnerDocumentUpdateEvent(), partnerDocumentUpdateEventHandler);
        eventProcessor.registerEventHandlers(new PartnerDocumentTypeUpdateEvent(), partnerDocumentTypeUpdateEventHandler);
        eventProcessor.registerEventHandlers(new VehicleUpdateEvent(), vehicleUpdateEventHandler);
        eventProcessor.registerEventHandlers(new VehicleDocumentUpdateEvent(), vehicleDocumentUpdateEventHandler);
        eventProcessor.registerEventHandlers(new VehicleDocumentTypeUpdateEvent(), vehicleDocumentTypeUpdateEventHandler);
        eventProcessor.registerEventHandlers(new VehicleDetailUpdateEvent(), vehicleDetailUpdateEventHandler);
        eventProcessor.registerEventHandlers(new VehicleTransferDetailUpdateEvent(), vehicleTransferDetailUpdateEventHandler);
        eventProcessor.registerEventHandlers(new VehicleAssetTransferDetailUpdateEvent(), vehicleAssetTransferDetailUpdateEventHandler);
        eventProcessor.registerEventHandlers(new VehicleAssetMappingUpdateEvent(), vehicleAssetMappingUpdateEventHandler);
        eventProcessor.registerEventHandlers(new BookingUpdateEvent(), bookingUpdateEventHandler);
        eventProcessor.registerEventHandlers(new LocationUpdateEvent(), locationUpdateEventHandler);


        eventProcessor.registerEvents(new AssetSaveEvent());
        eventProcessor.registerEvents(new AssetTypeSaveEvent());
        eventProcessor.registerEvents(new PartnerSaveEvent());
        eventProcessor.registerEvents(new PartnerDetailSaveEvent());
        eventProcessor.registerEvents(new PartnerDutySaveEvent());
        eventProcessor.registerEvents(new PartnerShiftSaveEvent());
        eventProcessor.registerEvents(new PartnerPayoutSaveEvent());
        eventProcessor.registerEvents(new PartnerDocumentSaveEvent());
        eventProcessor.registerEvents(new PartnerDocumentTypeSaveEvent());
        eventProcessor.registerEvents(new VehicleSaveEvent());
        eventProcessor.registerEvents(new VehicleDocumentSaveEvent());
        eventProcessor.registerEvents(new VehicleDocumentTypeSaveEvent());
        eventProcessor.registerEvents(new VehicleDetailSaveEvent());
        eventProcessor.registerEvents(new VehicleTransferDetailSaveEvent());
        eventProcessor.registerEvents(new VehicleAssetTransferDetailSaveEvent());
        eventProcessor.registerEvents(new VehicleAssetMappingSaveEvent());
        eventProcessor.registerEvents(new BookingSaveEvent());
        eventProcessor.registerEvents(new LocationSaveEvent());


        eventProcessor.registerEventHandlers(new AssetSaveEvent(), assetSaveEventHandler);
        eventProcessor.registerEventHandlers(new AssetTypeSaveEvent(), assetTypeSaveEventHandler);
        eventProcessor.registerEventHandlers(new PartnerSaveEvent(), partnerSaveEventHandler);
        eventProcessor.registerEventHandlers(new PartnerDetailSaveEvent(), partnerDetailSaveEventHandler);
        eventProcessor.registerEventHandlers(new PartnerDutySaveEvent(), partnerDutySaveEventHandler);
        eventProcessor.registerEventHandlers(new PartnerShiftSaveEvent(), partnerShiftSaveEventHandler);
        eventProcessor.registerEventHandlers(new PartnerPayoutSaveEvent(), partnerPayoutSaveEventHandler);
        eventProcessor.registerEventHandlers(new PartnerDocumentSaveEvent(), partnerDocumentSaveEventHandler);
        eventProcessor.registerEventHandlers(new PartnerDocumentTypeSaveEvent(), partnerDocumentTypeSaveEventHandler);
        eventProcessor.registerEventHandlers(new VehicleSaveEvent(), vehicleSaveEventHandler);
        eventProcessor.registerEventHandlers(new VehicleDocumentSaveEvent(), vehicleDocumentSaveEventHandler);
        eventProcessor.registerEventHandlers(new VehicleDocumentTypeSaveEvent(), vehicleDocumentTypeSaveEventHandler);
        eventProcessor.registerEventHandlers(new VehicleDetailSaveEvent(), vehicleDetailSaveEventHandler);
        eventProcessor.registerEventHandlers(new VehicleTransferDetailSaveEvent(), vehicleTransferDetailSaveEventHandler);
        eventProcessor.registerEventHandlers(new VehicleAssetTransferDetailSaveEvent(), vehicleAssetTransferDetailSaveEventHandler);
        eventProcessor.registerEventHandlers(new VehicleAssetMappingSaveEvent(), vehicleAssetMappingSaveEventHandler);
        eventProcessor.registerEventHandlers(new BookingSaveEvent(), bookingSaveEventHandler);
        eventProcessor.registerEventHandlers(new LocationSaveEvent(), locationSaveEventHandler);


        eventProcessor.registerTargets(CentralTarget.getTarget(eventProcessor));
        eventProcessor.registerTargets(CustomerTarget.getTarget(eventProcessor));
        return eventProcessor;
    }
}

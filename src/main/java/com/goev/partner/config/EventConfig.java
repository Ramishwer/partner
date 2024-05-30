package com.goev.partner.config;

import com.goev.partner.event.events.PartnerUpdateEvent;
import com.goev.partner.event.events.VehicleUpdateEvent;
import com.goev.partner.event.handlers.PartnerUpdateEventHandler;
import com.goev.partner.event.targets.AuthTarget;
import com.goev.partner.event.targets.CentralTarget;
import com.goev.lib.event.core.EventChannel;
import com.goev.lib.event.core.impl.APIEventChannel;
import com.goev.lib.event.service.EventProcessor;
import com.goev.lib.event.service.impl.SimpleEventProcessor;
import com.goev.lib.services.RestClient;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
@Slf4j
public class EventConfig {

    @Bean
    public EventProcessor getEventProcessor(EventChannel eventChannel,
                                            PartnerUpdateEventHandler partnerUpdateEventHandler) {
        SimpleEventProcessor eventProcessor = new SimpleEventProcessor();

        eventProcessor.registerEvents(new PartnerUpdateEvent());
        eventProcessor.registerEvents(new VehicleUpdateEvent());

        eventProcessor.registerEventHandlers(new PartnerUpdateEvent(),partnerUpdateEventHandler);


        eventProcessor.registerTargets(AuthTarget.getTarget(eventChannel));
        eventProcessor.registerTargets(CentralTarget.getTarget(eventChannel));
        return eventProcessor;
    }


    @Bean
    public EventChannel getEventChannel(RestClient restClient) {
        APIEventChannel eventChannel = new APIEventChannel();
        eventChannel.init(restClient);
        return eventChannel;
    }
}

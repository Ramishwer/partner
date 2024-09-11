package com.goev.partner.event.targets;

import com.goev.lib.event.core.EventChannelConfiguration;
import com.goev.lib.event.core.EventTarget;
import com.goev.lib.event.core.impl.APIEventChannel;
import com.goev.lib.event.service.EventProcessor;
import com.goev.lib.services.RestClient;
import com.goev.partner.config.SpringContext;
import com.goev.partner.constant.ApplicationConstants;


public class CustomerTarget extends EventTarget {
    private CustomerTarget() {
    }

    public static String getTargetName() {
        return "CUSTOMER";
    }

    public static CustomerTarget getTarget(EventProcessor eventProcessor) {
        APIEventChannel eventChannel = new APIEventChannel();
        eventChannel.init(SpringContext.getBean(RestClient.class));
        CustomerTarget centralTarget = new CustomerTarget();
        centralTarget.setChannel(eventChannel);
        centralTarget.setName(getTargetName());
        centralTarget.setConfig(EventChannelConfiguration.builder()
                .hostName(ApplicationConstants.CUSTOMER_URL)
                .base("/api/v1/internal")
                .path("/events")
                .authKey(ApplicationConstants.CLIENT_ID)
                .authSecret(ApplicationConstants.CLIENT_SECRET)
                .processor(eventProcessor)
                .build());
        return centralTarget;
    }
}

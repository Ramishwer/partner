package com.goev.partner.event.targets;

import com.goev.lib.event.core.EventChannel;
import com.goev.lib.event.core.EventChannelConfiguration;
import com.goev.lib.event.core.EventTarget;
import com.goev.partner.constant.ApplicationConstants;


public class CentralTarget extends EventTarget {
    private CentralTarget() {
    }

    public static String getTargetName() {
        return "CENTRAL";
    }

    public static CentralTarget getTarget(EventChannel eventChannel) {
        CentralTarget centralTarget = new CentralTarget();
        centralTarget.setChannel(eventChannel);
        centralTarget.setName(getTargetName());
        centralTarget.setConfig(EventChannelConfiguration.builder()
                .hostName(ApplicationConstants.CENTRAL_URL)
                .base("/api/v1/internal")
                .path("/events")
                .authKey(ApplicationConstants.CLIENT_ID)
                .authSecret(ApplicationConstants.CLIENT_SECRET)
                .build());
        return centralTarget;
    }
}

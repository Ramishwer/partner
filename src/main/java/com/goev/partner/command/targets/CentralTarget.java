package com.goev.partner.command.targets;

import com.goev.partner.config.SpringContext;
import com.goev.partner.constant.ApplicationConstants;
import com.goev.lib.command.core.CommandChannelConfiguration;
import com.goev.lib.command.core.CommandTarget;
import com.goev.lib.command.core.impl.APICommandChannel;
import com.goev.lib.command.service.CommandProcessor;
import com.goev.lib.services.RestClient;


public class CentralTarget extends CommandTarget {
    private CentralTarget() {
    }

    public static String getTargetName() {
        return "CENTRAL";
    }

    public static CentralTarget getTarget(CommandProcessor commandProcessor) {
        APICommandChannel commandChannel = new APICommandChannel();
        commandChannel.init(SpringContext.getBean(RestClient.class));
        CentralTarget centralTarget = new CentralTarget();
        centralTarget.setChannel(commandChannel);
        centralTarget.setName(getTargetName());
        centralTarget.setConfig(CommandChannelConfiguration.builder()
                .hostName(ApplicationConstants.CENTRAL_URL)
                .base("/api/v1/internal")
                .path("/events")
                .authKey(ApplicationConstants.CLIENT_ID)
                .authSecret(ApplicationConstants.CLIENT_SECRET)
                .processor(commandProcessor)
                .build());
        return centralTarget;
    }
}

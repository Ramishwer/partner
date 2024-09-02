package com.goev.partner.command.targets;

import com.goev.lib.command.core.CommandChannelConfiguration;
import com.goev.lib.command.core.CommandTarget;
import com.goev.lib.command.core.impl.APICommandChannel;
import com.goev.lib.command.service.CommandProcessor;
import com.goev.lib.services.RestClient;
import com.goev.partner.config.SpringContext;
import com.goev.partner.constant.ApplicationConstants;


public class BookingTarget extends CommandTarget {
    private BookingTarget() {
    }

    public static String getTargetName() {
        return "BOOKING";
    }

    public static BookingTarget getTarget(CommandProcessor commandProcessor) {
        APICommandChannel commandChannel = new APICommandChannel();
        commandChannel.init(SpringContext.getBean(RestClient.class));
        BookingTarget centralTarget = new BookingTarget();
        centralTarget.setChannel(commandChannel);
        centralTarget.setName(getTargetName());
        centralTarget.setConfig(CommandChannelConfiguration.builder()
                .hostName(ApplicationConstants.BOOKING_URL)
                .base("/api/v1/internal")
                .path("/commands")
                .authKey(ApplicationConstants.CLIENT_ID)
                .authSecret(ApplicationConstants.CLIENT_SECRET)
                .processor(commandProcessor)
                .build());
        return centralTarget;
    }
}

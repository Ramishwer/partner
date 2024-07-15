package com.goev.partner.command.targets;

import com.goev.lib.command.core.CommandChannelConfiguration;
import com.goev.lib.command.core.CommandTarget;
import com.goev.lib.command.core.impl.SelfCommandChannel;
import com.goev.lib.command.service.CommandProcessor;


public class PartnerTarget extends CommandTarget {
    private PartnerTarget() {
    }

    public static String getTargetName() {
        return "PARTNER";
    }

    public static PartnerTarget getTarget(CommandProcessor commandProcessor) {
        SelfCommandChannel commandChannel = new SelfCommandChannel();
        commandChannel.init();
        PartnerTarget partnerTarget = new PartnerTarget();
        partnerTarget.setChannel(commandChannel);
        partnerTarget.setName(getTargetName());
        partnerTarget.setConfig(CommandChannelConfiguration.builder()
                .processor(commandProcessor)
                .build());

        return partnerTarget;
    }
}

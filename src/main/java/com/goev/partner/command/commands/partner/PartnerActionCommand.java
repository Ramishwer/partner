package com.goev.partner.command.commands.partner;

import com.goev.lib.command.core.Command;
import com.goev.partner.command.commands.booking.BookingActionCommand;
import com.goev.partner.command.targets.PartnerTarget;
import com.goev.partner.dao.partner.detail.PartnerDao;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class PartnerActionCommand extends Command<PartnerDao> {

    @Override
    @PostConstruct
    public void init() {
        registerCommandTargets(PartnerTarget.getTargetName());
    }

    @Override
    public String getName() {
        return PartnerActionCommand.class.getName();
    }
}

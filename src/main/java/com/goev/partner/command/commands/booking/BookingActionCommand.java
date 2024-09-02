package com.goev.partner.command.commands.booking;

import com.goev.lib.command.core.Command;
import com.goev.partner.command.targets.BookingTarget;
import com.goev.partner.dao.partner.detail.PartnerDao;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class BookingActionCommand extends Command<PartnerDao> {

    @Override
    @PostConstruct
    public void init() {
        registerCommandTargets(BookingTarget.getTargetName());
    }

    @Override
    public String getName() {
        return BookingActionCommand.class.getName();
    }
}

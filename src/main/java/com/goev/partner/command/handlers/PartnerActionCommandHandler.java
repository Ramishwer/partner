package com.goev.partner.command.handlers;

import com.goev.lib.command.core.Command;
import com.goev.lib.command.core.CommandHandler;
import com.goev.partner.dto.partner.status.ActionDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@AllArgsConstructor
public class PartnerActionCommandHandler extends CommandHandler<ActionDto> {
    @Override
    public String onExecute(Command<ActionDto> command) {
        return null;
    }
}

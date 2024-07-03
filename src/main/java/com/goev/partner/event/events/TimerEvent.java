package com.goev.partner.event.events;

import com.goev.lib.event.core.Event;
import com.goev.partner.dao.asset.AssetDao;
import com.goev.partner.dto.TimerRequestDto;
import com.goev.partner.event.targets.CentralTarget;
import com.goev.partner.event.targets.TimerTarget;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class TimerEvent extends Event<TimerRequestDto> {

    @Override
    @PostConstruct
    public void init() {
        registerEventTargets(TimerTarget.getTargetName());
    }

    @Override
    public String getName() {
        return "TimerEvent";
    }

}

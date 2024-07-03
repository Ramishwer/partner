package com.goev.partner.event;

import com.goev.lib.event.core.Event;
import com.goev.lib.event.core.EventChannel;
import com.goev.lib.event.core.EventTarget;
import com.goev.lib.event.service.impl.SimpleEventProcessor;
import com.goev.partner.dto.TimerRequestDto;
import com.goev.partner.event.events.TimerEvent;
import com.goev.partner.event.targets.TimerTarget;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.stereotype.Component;

@Slf4j
@AllArgsConstructor
public class ExtendedEventProcessor extends SimpleEventProcessor {
    @Override
    public boolean scheduleEvent(Event event){
        if(event.getExecutionTime()<= DateTime.now().getMillis())
            return sendEvent(event);
        EventTarget target = TimerTarget.getTarget(this);

        TimerEvent timerEvent = new TimerEvent();
        timerEvent.setData(TimerRequestDto.builder()
                        .eventName(event.getName())
                        .data(event.getData())
                        .targets(event.getEventTargets())
                .build());
        timerEvent.setExecutionTime(event.getExecutionTime());
        target.getChannel().send(timerEvent,target);
        return true;
    }
}

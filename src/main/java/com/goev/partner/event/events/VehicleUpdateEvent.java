package com.goev.partner.event.events;


import com.goev.lib.event.core.Event;
import com.goev.partner.dao.vehicle.detail.VehicleDao;
import com.goev.partner.event.targets.CentralTarget;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@AllArgsConstructor
public class VehicleUpdateEvent extends Event<VehicleDao> {

    @Override
    public String getName() {
        return "VehicleUpdateEvent";
    }

    @Override
    @PostConstruct
    public void init() {
        registerEventTargets(CentralTarget.getTargetName());
    }

}

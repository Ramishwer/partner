package com.goev.partner.controller;

import com.goev.lib.dto.ResponseDto;
import com.goev.lib.dto.StatusDto;
import com.goev.lib.event.service.EventProcessor;
import com.goev.partner.utilities.RequestContext;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@AllArgsConstructor
public class InternalController {

    private final EventProcessor eventProcessor;

    @PostMapping("/api/v1/internal/events")
    public ResponseDto<Boolean> receivedEvent(@RequestBody String event, @RequestParam("name") String name, HttpServletRequest request) {

        log.info("Received Event :{}", event);
        RequestContext.setRequestSource("EVENT");
        RequestContext.setAuthUUID(request.getHeader("authUUID"));
        return new ResponseDto<>(
                StatusDto.builder().message("SUCCESS").build(), 200,
                eventProcessor.receiveEvent(event, name));
    }
}

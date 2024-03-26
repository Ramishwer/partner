package com.goev.partner.controller;

import com.goev.lib.dto.PasswordCredentialsDto;
import com.goev.lib.dto.ResponseDto;
import com.goev.lib.dto.StatusDto;
import com.goev.partner.dto.session.SessionDetailsDto;
import com.goev.partner.dto.session.SessionDto;
import com.goev.partner.service.SessionService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/api/v1/session-management")
public class SessionController {

    @Autowired
    private SessionService sessionService;

    @GetMapping("/sessions")
    public ResponseDto<List<SessionDto>> getSessions(){
        return new ResponseDto<>(StatusDto.builder().message("SUCCESS").build(),200, sessionService.getSessions());
    }
    @PostMapping("/sessions")
    public ResponseDto<SessionDto> createSession(@RequestBody PasswordCredentialsDto credentials){
        return new ResponseDto<>(StatusDto.builder().message("SUCCESS").build(),200, sessionService.createSession(credentials));
    }

    @PostMapping("/sessions/{session-uuid}/token")
    public ResponseDto<SessionDto> refreshSession(@PathVariable(value = "session-uuid")String sessionUUID, HttpServletRequest request){
        String refreshToken = request.getHeader("refresh-token");
        return new ResponseDto<>(StatusDto.builder().message("SUCCESS").build(),200, sessionService.refreshSessionForSessionUUID(sessionUUID,refreshToken));
    }

    @GetMapping("/sessions/{session-uuid}")
    public ResponseDto<SessionDetailsDto> getSession(@PathVariable(value = "session-uuid")String sessionUUID){
        return new ResponseDto<>(StatusDto.builder().message("SUCCESS").build(),200, sessionService.getSessionDetails(sessionUUID));
    }

    @DeleteMapping("/sessions/{session-uuid}")
    public ResponseDto<Boolean> deleteSession(@PathVariable(value = "session-uuid")String sessionUUID){
        return new ResponseDto<>(StatusDto.builder().message("SUCCESS").build(),200, sessionService.deleteSession(sessionUUID));
    }
}

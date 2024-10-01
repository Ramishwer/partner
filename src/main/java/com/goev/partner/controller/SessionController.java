package com.goev.partner.controller;

import com.goev.lib.dto.OtpCredentialsDto;
import com.goev.lib.dto.ResponseDto;
import com.goev.lib.dto.StatusDto;
import com.goev.partner.dto.session.SessionDetailsDto;
import com.goev.partner.dto.session.SessionDto;
import com.goev.partner.dto.session.SessionRequestDto;
import com.goev.partner.service.session.SessionService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/v1/partner-management")
public class SessionController {

    private final SessionService sessionService;

    @GetMapping("/sessions/otp")
    public ResponseDto<OtpCredentialsDto> getSessionOtp(@RequestParam("phoneNumber") String phoneNumber,
                                                        @RequestParam(value = "resend",required = false) Boolean resend,
                                                        @RequestParam(value = "resendType",required = false) String resendType) {
        return new ResponseDto<>(StatusDto.builder().message("SUCCESS").build(), 200, sessionService.getSessions(phoneNumber,resend,resendType));
    }

    @PostMapping("/sessions")
    public ResponseDto<SessionDetailsDto> createSession(@RequestBody SessionRequestDto sessionRequestDto) {
        return new ResponseDto<>(StatusDto.builder().message("SUCCESS").build(), 200, sessionService.createSession(sessionRequestDto));
    }

    @GetMapping("/sessions/{session-uuid}/token")
    public ResponseDto<SessionDto> refreshSession(@PathVariable(value = "session-uuid") String sessionUUID) {
        return new ResponseDto<>(StatusDto.builder().message("SUCCESS").build(), 200, sessionService.refreshSessionForSessionUUID(sessionUUID));
    }

    @GetMapping("/sessions/{session-uuid}")
    public ResponseDto<SessionDetailsDto> getSession(@PathVariable(value = "session-uuid") String sessionUUID) {
        return new ResponseDto<>(StatusDto.builder().message("SUCCESS").build(), 200, sessionService.getSessionDetails(sessionUUID));
    }

    @DeleteMapping("/sessions/{session-uuid}")
    public ResponseDto<Boolean> deleteSession(@PathVariable(value = "session-uuid") String sessionUUID) {
        return new ResponseDto<>(StatusDto.builder().message("SUCCESS").build(), 200, sessionService.deleteSession(sessionUUID));
    }
}

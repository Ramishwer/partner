package com.goev.partner.service.session;

import com.goev.lib.dto.OtpCredentialsDto;
import com.goev.partner.dto.session.SessionDetailsDto;
import com.goev.partner.dto.session.SessionDto;
import com.goev.partner.dto.session.SessionRequestDto;

public interface SessionService {
    SessionDetailsDto createSession(SessionRequestDto credentials);

    SessionDto refreshSessionForSessionUUID(String sessionUUID);

    SessionDetailsDto getSessionDetails(String sessionUUID);

    Boolean deleteSession(String sessionUUID);

    OtpCredentialsDto getSessions(String phoneNumber);
}

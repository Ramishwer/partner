package com.goev.partner.service;

import com.goev.lib.dto.PasswordCredentialsDto;
import com.goev.partner.dto.session.SessionDetailsDto;
import com.goev.partner.dto.session.SessionDto;

import java.util.List;

public interface SessionService {
    SessionDto createSession(PasswordCredentialsDto credentials);
    SessionDto refreshSessionForSessionUUID(String sessionUUID, String refreshToken);
    SessionDetailsDto getSessionDetails(String sessionUUID);
    Boolean deleteSession(String sessionUUID);

    List<SessionDto> getSessions();
}

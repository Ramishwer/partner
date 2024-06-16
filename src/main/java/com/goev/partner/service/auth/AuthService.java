package com.goev.partner.service.auth;

import com.goev.lib.dto.OtpCredentialsDto;
import com.goev.partner.dao.partner.detail.PartnerSessionDao;
import com.goev.partner.dto.auth.AuthClientDto;
import com.goev.partner.dto.auth.AuthCredentialDto;
import com.goev.partner.dto.auth.AuthUserDto;
import com.goev.partner.dto.session.SessionDetailsDto;
import com.goev.partner.dto.session.SessionDto;
import com.goev.lib.dto.PasswordCredentialsDto;

public interface AuthService {
    SessionDetailsDto getSessionDetails(PartnerSessionDao partnerSessionDao);

    AuthClientDto getClient(String token);

    boolean deleteSession(PartnerSessionDao partnerSessionDao);

    SessionDto refreshSession(PartnerSessionDao partnerSessionDao);

    SessionDto createSession(OtpCredentialsDto credentials, String authUUID);

    AuthCredentialDto initiateSession(String phoneNumber);
}

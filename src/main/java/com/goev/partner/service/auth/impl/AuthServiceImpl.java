package com.goev.partner.service.auth.impl;

import com.amazonaws.util.Base64;
import com.goev.lib.dto.OtpCredentialsDto;
import com.goev.lib.dto.ResponseDto;
import com.goev.lib.services.RestClient;
import com.goev.partner.constant.ApplicationConstants;
import com.goev.partner.dao.partner.detail.PartnerSessionDao;
import com.goev.partner.dto.auth.AuthClientDto;
import com.goev.partner.dto.session.SessionDetailsDto;
import com.goev.partner.dto.session.SessionDto;
import com.goev.partner.dto.auth.AuthCredentialDto;
import com.goev.partner.dto.auth.AuthCredentialTypeDto;
import com.goev.partner.service.auth.AuthService;
import com.goev.partner.utilities.RequestContext;
import com.google.gson.reflect.TypeToken;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Service
@AllArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {
    private final RestClient restClient;

    @Override
    public SessionDetailsDto getSessionDetails(PartnerSessionDao partnerSessionDao) {
        String url = ApplicationConstants.AUTH_URL + "/api/v1/session-management/sessions/" + partnerSessionDao.getAuthSessionUuid();
        HttpHeaders header = new HttpHeaders();
        header.set("Authorization", RequestContext.getAccessToken());
        String responseStr = restClient.get(url, header, String.class, true);
        ResponseDto<SessionDetailsDto> session = ApplicationConstants.GSON.fromJson(responseStr, new TypeToken<ResponseDto<SessionDetailsDto>>() {
        }.getType());
        if (session == null)
            return null;
        return session.getData();
    }


    @Override
    public AuthClientDto getClient(String token) {
        String url = ApplicationConstants.AUTH_URL + "/api/v1/client-management/clients";
        HttpHeaders header = new HttpHeaders();
        header.set("Authorization", token);
        String responseStr = restClient.get(url, header, String.class, true);
        ResponseDto<AuthClientDto> client = ApplicationConstants.GSON.fromJson(responseStr, new TypeToken<ResponseDto<AuthClientDto>>() {
        }.getType());
        if (client == null)
            return null;
        return client.getData();
    }


    @Override
    public boolean deleteSession(PartnerSessionDao partnerSessionDao) {
        String url = ApplicationConstants.AUTH_URL + "/api/v1/session-management/sessions/" + partnerSessionDao.getAuthSessionUuid();
        HttpHeaders header = new HttpHeaders();
        header.set("Refresh-Token", RequestContext.getRefreshToken());
        header.set("Authorization", RequestContext.getAccessToken());
        String response = restClient.delete(url, header, String.class, true);
        return response != null;
    }


    @Override
    public SessionDto refreshSession(PartnerSessionDao partnerSessionDao) {
        String url = ApplicationConstants.AUTH_URL + "/api/v1/session-management/sessions/" + partnerSessionDao.getAuthSessionUuid() + "/token";
        HttpHeaders header = new HttpHeaders();
        header.set(HttpHeaders.AUTHORIZATION, "Basic " + Base64.encodeAsString((ApplicationConstants.CLIENT_ID + ":" + ApplicationConstants.CLIENT_SECRET).getBytes(StandardCharsets.UTF_8)));
        header.set("Refresh-Token", RequestContext.getRefreshToken());
        String response = restClient.get(url, header, String.class, true);
        ResponseDto<SessionDto> session = ApplicationConstants.GSON.fromJson(response, new TypeToken<ResponseDto<SessionDto>>() {
        }.getType());
        if (session == null)
            return null;
        return session.getData();
    }

    @Override
    public SessionDto createSession(OtpCredentialsDto credentials, String authUUID) {
        String url = ApplicationConstants.AUTH_URL + "/api/v1/session-management/sessions";
        HttpHeaders header = new HttpHeaders();
        header.set(HttpHeaders.AUTHORIZATION, "Basic " + Base64.encodeAsString((ApplicationConstants.CLIENT_ID + ":" + ApplicationConstants.CLIENT_SECRET).getBytes(StandardCharsets.UTF_8)));
        String response = restClient.post(url, header, AuthCredentialDto.builder()
                .authCredentialType(AuthCredentialTypeDto.builder()
                        .name(ApplicationConstants.CREDENTIAL_TYPE_NAME)
                        .uuid(ApplicationConstants.CREDENTIAL_TYPE_UUID)
                        .build())
                .authKey(credentials.getPhone())
                .authSecret(credentials.getOtp())
                .authUUID(authUUID)
                .build(), String.class, true);
        ResponseDto<SessionDto> session = ApplicationConstants.GSON.fromJson(response, new TypeToken<ResponseDto<SessionDto>>() {
        }.getType());

        if (session == null)
            return null;
        return session.getData();
    }

    @Override
    public AuthCredentialDto initiateSession(String phoneNumber) {
        String url = ApplicationConstants.AUTH_URL + "/api/v1/session-management/sessions/credential-types/" + ApplicationConstants.CREDENTIAL_TYPE_UUID + "?phoneNumber=" + phoneNumber;
        HttpHeaders header = new HttpHeaders();
        header.set(HttpHeaders.AUTHORIZATION, "Basic " + Base64.encodeAsString((ApplicationConstants.CLIENT_ID + ":" + ApplicationConstants.CLIENT_SECRET).getBytes(StandardCharsets.UTF_8)));
        String response = restClient.get(url, header, String.class, true);
        ResponseDto<AuthCredentialDto> credentials = ApplicationConstants.GSON.fromJson(response, new TypeToken<ResponseDto<AuthCredentialDto>>() {
        }.getType());

        if (credentials == null)
            return null;
        return credentials.getData();

    }
}

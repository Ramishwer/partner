package com.goev.partner.service.session.impl;


import com.amazonaws.util.Base64;
import com.goev.lib.dto.OtpCredentialsDto;
import com.goev.lib.dto.ResponseDto;
import com.goev.lib.exceptions.ResponseException;
import com.goev.lib.services.RestClient;
import com.goev.partner.constant.ApplicationConstants;
import com.goev.partner.dao.partner.auth.AuthCredentialDto;
import com.goev.partner.dao.partner.auth.AuthCredentialTypeDto;
import com.goev.partner.dao.partner.detail.PartnerDao;
import com.goev.partner.dao.partner.detail.PartnerDeviceDao;
import com.goev.partner.dao.partner.detail.PartnerSessionDao;
import com.goev.partner.dto.partner.detail.PartnerDeviceDto;
import com.goev.partner.dto.session.SessionDetailsDto;
import com.goev.partner.dto.session.SessionDto;
import com.goev.partner.dto.session.SessionRequestDto;
import com.goev.partner.repository.partner.detail.PartnerDeviceRepository;
import com.goev.partner.repository.partner.detail.PartnerRepository;
import com.goev.partner.repository.partner.detail.PartnerSessionRepository;
import com.goev.partner.service.session.SessionService;
import com.goev.partner.utilities.RequestContext;
import com.google.gson.reflect.TypeToken;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;


@Slf4j
@Service
@AllArgsConstructor
public class SessionServiceImpl implements SessionService {
    private final PartnerRepository partnerRepository;
    private final PartnerSessionRepository partnerSessionRepository;
    private final RestClient restClient;
    private final PartnerDeviceRepository partnerDeviceRepository;

    @Override
    public SessionDetailsDto createSession(SessionRequestDto sessionRequestDto) {
        PartnerDao partner = partnerRepository.findByPhoneNumber(sessionRequestDto.getCredentials().getPhone());
        if (partner == null)
            throw new ResponseException("User does not exist");
        String url = ApplicationConstants.AUTH_URL + "/api/v1/session-management/sessions";
        HttpHeaders header = new HttpHeaders();
        header.set(HttpHeaders.AUTHORIZATION, "Basic " + Base64.encodeAsString((ApplicationConstants.CLIENT_ID + ":" + ApplicationConstants.CLIENT_SECRET).getBytes(StandardCharsets.UTF_8)));
        String response = restClient.post(url, header, AuthCredentialDto.builder()
                .authCredentialType(AuthCredentialTypeDto.builder()
                        .name(ApplicationConstants.CREDENTIAL_TYPE_NAME)
                        .uuid(ApplicationConstants.CREDENTIAL_TYPE_UUID)
                        .build())
                .authKey(sessionRequestDto.getCredentials().getPhone())
                .authSecret(sessionRequestDto.getCredentials().getOtp())
                .authUUID(partner.getAuthUuid())
                .build(), String.class, true);
        ResponseDto<SessionDto> session = ApplicationConstants.GSON.fromJson(response, new TypeToken<ResponseDto<SessionDto>>() {
        }.getType());
        if (session == null || session.getData() == null)
            throw new ResponseException("Invalid Credentials");
        SessionDto sessionDto = session.getData();
        PartnerSessionDao partnerSessionDao = new PartnerSessionDao();
        partnerSessionDao.setAuthSessionUuid(sessionDto.getUuid());
        partnerSessionDao.setPartnerId(partner.getId());
        partnerSessionDao.setLastActiveTime(DateTime.now());
        partnerSessionDao = partnerSessionRepository.save(partnerSessionDao);
        PartnerDeviceDao partnerDeviceDao = getPartnerDeviceDao(sessionRequestDto.getDeviceDetais());
        partnerDeviceDao.setPartnerId(partner.getId());
        partnerDeviceDao.setPartnerSessionId(partnerSessionDao.getId());
        partnerDeviceDao = partnerDeviceRepository.save(partnerDeviceDao);
        return SessionDetailsDto.builder()
                .details(SessionDto.builder()
                        .accessToken(sessionDto.getAccessToken())
                        .refreshToken(sessionDto.getRefreshToken())
                        .partnerUUID(partner.getUuid())
                        .uuid(partnerSessionDao.getUuid())
                        .build())
                .deviceDetails(PartnerDeviceDto.builder().uuid(partnerDeviceDao.getUuid()).build())
                .build();
    }

    private PartnerDeviceDao getPartnerDeviceDao(PartnerDeviceDto deviceDetais) {
        PartnerDeviceDao partnerDeviceDao = new PartnerDeviceDao();
        partnerDeviceDao.setFcmToken(deviceDetais.getFcmToken());
        partnerDeviceDao.setImei1(deviceDetais.getImei1());
        partnerDeviceDao.setImei2(deviceDetais.getImei2());
        partnerDeviceDao.setScreenHeight(deviceDetais.getScreenHeight());
        partnerDeviceDao.setScreenWidth(deviceDetais.getScreenWidth());
        partnerDeviceDao.setScreenDpi(deviceDetais.getScreenDpi());
        partnerDeviceDao.setAppVersion(deviceDetais.getAppVersion());
        partnerDeviceDao.setPlatform(deviceDetais.getPlatform());
        partnerDeviceDao.setBrand(deviceDetais.getBrand());
        partnerDeviceDao.setModel(deviceDetais.getModel());
        partnerDeviceDao.setCarrier(deviceDetais.getCarrier());
        partnerDeviceDao.setOsVersion(deviceDetais.getOsVersion());
        partnerDeviceDao.setGms(deviceDetais.getGms());
        partnerDeviceDao.setManufacturer(deviceDetais.getManufacturer());
        partnerDeviceDao.setCity(deviceDetais.getCity());
        partnerDeviceDao.setDistinctId(deviceDetais.getDistinctId());

        return partnerDeviceDao;
    }

    @Override
    public SessionDto refreshSessionForSessionUUID(String sessionUUID) {

        PartnerSessionDao partnerSessionDao = partnerSessionRepository.findByUUID(sessionUUID);
        if (partnerSessionDao == null)
            throw new ResponseException("Token Expired");
        if (RequestContext.getRefreshToken() == null)
            throw new ResponseException("Token Expired");
        String url = ApplicationConstants.AUTH_URL + "/api/v1/session-management/sessions/" + partnerSessionDao.getAuthSessionUuid() + "/token";
        HttpHeaders header = new HttpHeaders();
        header.set(HttpHeaders.AUTHORIZATION, "Basic " + Base64.encodeAsString((ApplicationConstants.CLIENT_ID + ":" + ApplicationConstants.CLIENT_SECRET).getBytes(StandardCharsets.UTF_8)));
        header.set("Refresh-Token", RequestContext.getRefreshToken());
        String response = restClient.get(url, header, String.class, true);
        ResponseDto<SessionDto> session = ApplicationConstants.GSON.fromJson(response, new TypeToken<ResponseDto<SessionDto>>() {
        }.getType());

        if (session == null || session.getData() == null)
            throw new ResponseException("Token Expired");
        SessionDto sessionDto = session.getData();
        PartnerSessionDao sessionDao = new PartnerSessionDao();
        sessionDao.setAuthSessionUuid(sessionDto.getUuid());
        sessionDao.setPartnerId(sessionDao.getPartnerId());
        sessionDao.setLastActiveTime(DateTime.now());
        sessionDao = partnerSessionRepository.save(sessionDao);
        partnerSessionRepository.delete(partnerSessionDao.getId());

        PartnerDao partner = partnerRepository.findById(partnerSessionDao.getPartnerId());
        return SessionDto.builder()
                .accessToken(sessionDto.getAccessToken())
                .refreshToken(sessionDto.getRefreshToken())
                .expiresIn(sessionDto.getExpiresIn())
                .partnerUUID(partner.getUuid())
                .uuid(sessionDao.getUuid())
                .build();
    }

    @Override
    public SessionDetailsDto getSessionDetails(String sessionUUID) {
        PartnerSessionDao partnerSessionDao = RequestContext.getPartnerSession();
        if (partnerSessionDao == null)
            throw new ResponseException("Token Expired");
        partnerSessionDao.setLastActiveTime(DateTime.now());
        partnerSessionDao = partnerSessionRepository.update(partnerSessionDao);

        PartnerDao partner = partnerRepository.findById(partnerSessionDao.getPartnerId());

        return SessionDetailsDto.builder()
                .details(SessionDto.builder()
                        .partnerUUID(partner.getUuid())
                        .uuid(partnerSessionDao.getUuid())
                        .build())
                .build();
    }

    @Override
    public Boolean deleteSession(String sessionUUID) {
        PartnerSessionDao partnerSessionDao = RequestContext.getPartnerSession();
        if (partnerSessionDao == null)
            throw new ResponseException("Token Expired");
        String url = ApplicationConstants.AUTH_URL + "/api/v1/session-management/sessions/" + partnerSessionDao.getAuthSessionUuid();
        HttpHeaders header = new HttpHeaders();
        header.set("Refresh-Token", RequestContext.getRefreshToken());
        header.set("Authorization", RequestContext.getAccessToken());
        String response = restClient.delete(url, header, String.class, true);
        partnerSessionRepository.delete(partnerSessionDao.getId());
        return true;
    }

    @Override
    public OtpCredentialsDto getSessions(String phoneNumber) {
        PartnerDao partner = partnerRepository.findByPhoneNumber(phoneNumber);
        if (partner == null)
            throw new ResponseException("User does not exist");
        String url = ApplicationConstants.AUTH_URL + "/api/v1/session-management/sessions/credential-types/" + ApplicationConstants.CREDENTIAL_TYPE_UUID + "?phoneNumber=" + phoneNumber;
        HttpHeaders header = new HttpHeaders();
        header.set(HttpHeaders.AUTHORIZATION, "Basic " + Base64.encodeAsString((ApplicationConstants.CLIENT_ID + ":" + ApplicationConstants.CLIENT_SECRET).getBytes(StandardCharsets.UTF_8)));
        String response = restClient.get(url, header, String.class, true);
        ResponseDto<AuthCredentialDto> session = ApplicationConstants.GSON.fromJson(response, new TypeToken<ResponseDto<AuthCredentialDto>>() {
        }.getType());

        if (session == null || session.getData() == null)
            throw new ResponseException("User does not exist");
        OtpCredentialsDto result = new OtpCredentialsDto();
        result.setOtp(session.getData().getAuthSecret());
        result.setPhone(session.getData().getAuthKey());

        return result;
    }
}

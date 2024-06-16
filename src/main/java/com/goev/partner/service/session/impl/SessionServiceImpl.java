package com.goev.partner.service.session.impl;


import com.goev.lib.dto.OtpCredentialsDto;
import com.goev.lib.exceptions.ResponseException;
import com.goev.partner.dao.partner.detail.PartnerDao;
import com.goev.partner.dao.partner.detail.PartnerDeviceDao;
import com.goev.partner.dao.partner.detail.PartnerSessionDao;
import com.goev.partner.dto.auth.AuthCredentialDto;
import com.goev.partner.dto.partner.detail.PartnerDeviceDto;
import com.goev.partner.dto.session.SessionDetailsDto;
import com.goev.partner.dto.session.SessionDto;
import com.goev.partner.dto.session.SessionRequestDto;
import com.goev.partner.repository.partner.detail.PartnerDeviceRepository;
import com.goev.partner.repository.partner.detail.PartnerRepository;
import com.goev.partner.repository.partner.detail.PartnerSessionRepository;
import com.goev.partner.service.auth.AuthService;
import com.goev.partner.service.session.SessionService;
import com.goev.partner.utilities.RequestContext;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@AllArgsConstructor
public class SessionServiceImpl implements SessionService {
    private final PartnerRepository partnerRepository;
    private final PartnerSessionRepository partnerSessionRepository;
    private final PartnerDeviceRepository partnerDeviceRepository;
    private final AuthService authService;

    @Override
    public SessionDetailsDto createSession(SessionRequestDto sessionRequestDto) {
        PartnerDao partner = partnerRepository.findByPhoneNumber(sessionRequestDto.getCredentials().getPhone());
        if (partner == null)
            throw new ResponseException("User does not exist");

        SessionDto sessionDto = authService.createSession(sessionRequestDto.getCredentials(), partner.getAuthUuid());
        if (sessionDto == null)
            throw new ResponseException("Invalid Credentials");
        PartnerSessionDao partnerSessionDao = new PartnerSessionDao();
        partnerSessionDao.setAuthSessionUuid(sessionDto.getUuid());
        partnerSessionDao.setPartnerId(partner.getId());
        partnerSessionDao.setLastActiveTime(DateTime.now());
        partnerSessionDao = partnerSessionRepository.save(partnerSessionDao);
        PartnerDeviceDao partnerDeviceDao = getPartnerDeviceDao(sessionRequestDto.getDeviceDetails());
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
        SessionDto sessionDto = authService.refreshSession(partnerSessionDao);
        if (sessionDto == null)
            throw new ResponseException("Token Expired");
        PartnerSessionDao sessionDao = new PartnerSessionDao();
        sessionDao.setAuthSessionUuid(sessionDto.getUuid());
        sessionDao.setPartnerId(partnerSessionDao.getPartnerId());
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

        partnerSessionRepository.delete(partnerSessionDao.getId());
        return authService.deleteSession(partnerSessionDao);
    }

    @Override
    public OtpCredentialsDto getSessions(String phoneNumber) {
        PartnerDao partner = partnerRepository.findByPhoneNumber(phoneNumber);
        if (partner == null)
            throw new ResponseException("User does not exist");


        AuthCredentialDto credentialDto = authService.initiateSession(phoneNumber);

        if (credentialDto == null)
            throw new ResponseException("User does not exist");
        OtpCredentialsDto result = new OtpCredentialsDto();
        result.setOtp(credentialDto.getAuthSecret());
        result.setPhone(credentialDto.getAuthKey());

        return result;
    }
}

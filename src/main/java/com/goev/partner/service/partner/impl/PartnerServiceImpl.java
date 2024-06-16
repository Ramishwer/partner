package com.goev.partner.service.partner.impl;

import com.goev.lib.dto.LatLongDto;
import com.goev.lib.exceptions.ResponseException;
import com.goev.lib.utilities.ApplicationContext;
import com.goev.partner.constant.ApplicationConstants;
import com.goev.partner.dao.location.LocationDao;
import com.goev.partner.dao.partner.detail.PartnerDao;
import com.goev.partner.dao.partner.detail.PartnerDetailDao;
import com.goev.partner.dto.location.LocationDto;
import com.goev.partner.dto.partner.PartnerViewDto;
import com.goev.partner.dto.partner.detail.PartnerDetailDto;
import com.goev.partner.dto.partner.detail.PartnerDto;
import com.goev.partner.dto.partner.status.ActionDto;
import com.goev.partner.repository.location.LocationRepository;
import com.goev.partner.repository.partner.detail.PartnerDetailRepository;
import com.goev.partner.repository.partner.detail.PartnerRepository;
import com.goev.partner.service.partner.PartnerService;
import com.goev.partner.utilities.S3Utils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class PartnerServiceImpl implements PartnerService {
    private final PartnerRepository partnerRepository;
    private final PartnerDetailRepository partnerDetailRepository;
    private final LocationRepository locationRepository;
    private final S3Utils s3;


    @Override
    public PartnerDetailDto getPartnerDetails(String partnerUUID) {
        PartnerDao partner = partnerRepository.findByAuthUUID(ApplicationContext.getAuthUUID());
        if (partner == null)
            throw new ResponseException("No partner found for Id :" + partnerUUID);

        PartnerDetailDao partnerDetailDao = partnerDetailRepository.findById(partner.getPartnerDetailsId());

        if (partnerDetailDao == null)
            throw new ResponseException("No partner details found for Id :" + partnerUUID);


        PartnerDetailDto result = PartnerDetailDto.builder().build();
        setPartnerDetails(result, partner, partnerDetailDao);
        setPartnerHomeLocation(result, partnerDetailDao.getHomeLocationId());

        return result;
    }


    private void setPartnerDetails(PartnerDetailDto result, PartnerDao partnerDao, PartnerDetailDao partnerDetails) {
        PartnerViewDto partnerDto = new PartnerViewDto();
        partnerDto.setPunchId(partnerDao.getPunchId());
        partnerDto.setState(partnerDao.getState());
        partnerDto.setUuid(partnerDao.getUuid());
        partnerDto.setState(partnerDao.getState());
        partnerDto.setPhoneNumber(partnerDao.getPhoneNumber());
        partnerDto.setProfileUrl(partnerDao.getProfileUrl());


        if (partnerDetails == null)
            return;

        partnerDto.setFirstName(partnerDetails.getFirstName());
        partnerDto.setLastName(partnerDetails.getLastName());

        result.setPartner(partnerDto);
        result.setProfileUrl(partnerDetails.getProfileUrl());
        result.setJoiningDate(partnerDetails.getJoiningDate());
        result.setDlNumber(partnerDetails.getDlNumber());
        result.setDlExpiryDate(partnerDetails.getDlExpiryDate());
        result.setRemark(partnerDetails.getRemark());
        result.setOnboardingDate(partnerDetails.getOnboardingDate());
        result.setDeboardingDate(partnerDetails.getDeboardingDate());
        result.setUuid(partnerDao.getUuid());
        result.setState(partnerDao.getState());
        result.setDrivingTestStatus(partnerDetails.getDrivingTestStatus());
        result.setInterviewDate(partnerDetails.getInterviewDate());
        result.setSelectionStatus(partnerDetails.getSelectionStatus());
        result.setIsVerified(partnerDetails.getIsVerified());
        result.setSourceOfLead(partnerDetails.getSourceOfLead());
        result.setSourceOfLeadType(partnerDetails.getSourceOfLeadType());
        result.setFathersName(partnerDetails.getFathersName());
        result.setLocalAddress(partnerDetails.getLocalAddress());
        result.setPermanentAddress(partnerDetails.getPermanentAddress());
    }


    private void setPartnerHomeLocation(PartnerDetailDto result, Integer homeLocationId) {
        LocationDao locationDao = locationRepository.findById(homeLocationId);
        if (locationDao == null)
            return;
        result.setHomeLocation(LocationDto.builder()
                .coordinates(LatLongDto.builder()
                        .longitude(locationDao.getLongitude().doubleValue())
                        .latitude(locationDao.getLatitude().doubleValue())
                        .build())
                .name(locationDao.getName())
                .type(locationDao.getType())
                .uuid(locationDao.getUuid())
                .build());
    }


    @Override
    public PartnerDto getPartner(String partnerUUID) {
        PartnerDao partner = partnerRepository.findByAuthUUID(ApplicationContext.getAuthUUID());
        if (partner == null)
            throw new ResponseException("No partner found for Id :" + partnerUUID);

        return PartnerDto.fromDao(partner);
    }

    @Override
    public PartnerDto updatePartner(String partnerUUID, ActionDto actionDto) {
        PartnerDao partner = partnerRepository.findByAuthUUID(ApplicationContext.getAuthUUID());
        if (partner == null)
            throw new ResponseException("No partner found for Id :" + partnerUUID);

        switch (actionDto.getAction()) {
            case CHECK_IN -> {
                partner = checkin(partner, actionDto);
            }
            case SELECT_VEHICLE -> {
                partner = selectVehicle(partner, actionDto);
            }
            case RETURN_VEHICLE -> {
                partner = returnVehicle(partner, actionDto);
            }
            case SUBMIT_CHECKLIST -> {
                partner = submitChecklist(partner, actionDto);
            }
            case GO_ONLINE -> {
                partner = goOnline(partner, actionDto);
            }
            case PAUSE -> {
                partner = pause(partner, actionDto);
            }
            case UNPAUSE -> {
                partner = unPause(partner, actionDto);
            }
            case ENROUTE -> {
                partner = enroute(partner, actionDto);
            }
            case ARRIVE -> {
                partner = arrive(partner, actionDto);
            }
            case START -> {
                partner = start(partner, actionDto);
            }
            case END -> {
                partner = end(partner, actionDto);
            }
            case COMPLETE -> {
                partner = complete(partner, actionDto);
            }
            case GO_OFFLINE -> {
                partner = goOffline(partner, actionDto);
            }
            case CHECK_OUT -> {
                partner = checkOut(partner, actionDto);
            }
        }

        return PartnerDto.fromDao(partner);
    }

    @Override
    public PartnerDetailDto updatePartnerDetails(String partnerUUID, PartnerDetailDto partnerDetailDto) {
        PartnerDao partner = partnerRepository.findByAuthUUID(ApplicationContext.getAuthUUID());
        if (partner == null)
            throw new ResponseException("No partner found for Id :" + partnerUUID);

        PartnerDetailDao partnerDetailDao = partnerDetailRepository.findById(partner.getPartnerDetailsId());

        if (partnerDetailDto.getProfileUrl() != null) {
            partnerDetailDao.setProfileUrl(s3.getUrlForPath(partnerDetailDto.getProfileUrl(), "profile"));
        }
        partnerDetailRepository.update(partnerDetailDao);

        partner.setProfileUrl(partnerDetailDao.getProfileUrl());
        PartnerViewDto partnerViewDto = PartnerViewDto.fromDao(partner);
        partnerViewDto.setProfileUrl(partnerDetailDao.getProfileUrl());
        partner.setViewInfo(ApplicationConstants.GSON.toJson(partnerViewDto));
        partnerRepository.update(partner);
        PartnerDetailDto result = PartnerDetailDto.builder().build();
        setPartnerDetails(result, partner, partnerDetailDao);
        setPartnerHomeLocation(result, partnerDetailDao.getHomeLocationId());
        return result;
    }

    private PartnerDao checkOut(PartnerDao partner, ActionDto actionDto) {
        return null;
    }

    private PartnerDao goOffline(PartnerDao partner, ActionDto actionDto) {
        return null;
    }

    private PartnerDao complete(PartnerDao partner, ActionDto actionDto) {
        return null;
    }

    private PartnerDao end(PartnerDao partner, ActionDto actionDto) {
        return null;
    }

    private PartnerDao start(PartnerDao partner, ActionDto actionDto) {
        return null;
    }

    private PartnerDao arrive(PartnerDao partner, ActionDto actionDto) {
        return null;
    }

    private PartnerDao enroute(PartnerDao partner, ActionDto actionDto) {
        return null;
    }

    private PartnerDao unPause(PartnerDao partner, ActionDto actionDto) {
        return null;
    }

    private PartnerDao pause(PartnerDao partner, ActionDto actionDto) {
        return null;
    }

    private PartnerDao goOnline(PartnerDao partner, ActionDto actionDto) {
        return null;
    }

    private PartnerDao submitChecklist(PartnerDao partner, ActionDto actionDto) {
        return null;
    }

    private PartnerDao selectVehicle(PartnerDao partner, ActionDto actionDto) {
        return null;
    }

    private PartnerDao returnVehicle(PartnerDao partner, ActionDto actionDto) {
        return null;
    }

    private PartnerDao checkin(PartnerDao partner, ActionDto actionDto) {
        return null;
    }
}

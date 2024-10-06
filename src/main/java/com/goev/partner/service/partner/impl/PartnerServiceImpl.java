package com.goev.partner.service.partner.impl;

import com.goev.lib.dto.LatLongDto;
import com.goev.lib.dto.StatsDto;
import com.goev.lib.exceptions.ResponseException;
import com.goev.lib.utilities.ApplicationContext;
import com.goev.lib.utilities.DistanceUtils;
import com.goev.partner.constant.ApplicationConstants;
import com.goev.partner.dao.booking.BookingDao;
import com.goev.partner.dao.location.LocationDao;
import com.goev.partner.dao.partner.detail.PartnerDao;
import com.goev.partner.dao.partner.detail.PartnerDetailDao;
import com.goev.partner.dao.partner.duty.PartnerDutyDao;
import com.goev.partner.dao.partner.duty.PartnerShiftDao;
import com.goev.partner.dao.vehicle.detail.VehicleDao;
import com.goev.partner.dto.booking.BookingViewDto;
import com.goev.partner.dto.common.QrValueDto;
import com.goev.partner.dto.location.LocationDto;
import com.goev.partner.dto.partner.PartnerViewDto;
import com.goev.partner.dto.partner.detail.PartnerDetailDto;
import com.goev.partner.dto.partner.detail.PartnerDto;
import com.goev.partner.dto.partner.duty.PartnerDutyDto;
import com.goev.partner.dto.partner.duty.PartnerDutyVehicleDetailsDto;
import com.goev.partner.dto.partner.status.ActionDto;
import com.goev.partner.dto.vehicle.VehicleStatsDto;
import com.goev.partner.dto.vehicle.VehicleViewDto;
import com.goev.partner.enums.booking.BookingStatus;
import com.goev.partner.enums.booking.BookingSubStatus;
import com.goev.partner.enums.partner.*;
import com.goev.partner.enums.vehicle.VehicleStatus;
import com.goev.partner.repository.booking.BookingRepository;
import com.goev.partner.repository.location.LocationRepository;
import com.goev.partner.repository.partner.detail.PartnerDetailRepository;
import com.goev.partner.repository.partner.detail.PartnerRepository;
import com.goev.partner.repository.partner.duty.PartnerDutyRepository;
import com.goev.partner.repository.partner.duty.PartnerShiftRepository;
import com.goev.partner.repository.vehicle.detail.VehicleRepository;
import com.goev.partner.service.partner.PartnerService;
import com.goev.partner.utilities.S3Utils;
import com.google.gson.reflect.TypeToken;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Type;
import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class PartnerServiceImpl implements PartnerService {
    private final PartnerRepository partnerRepository;
    private final PartnerDetailRepository partnerDetailRepository;
    private final PartnerShiftRepository partnerShiftRepository;
    private final PartnerDutyRepository partnerDutyRepository;
    private final LocationRepository locationRepository;
    private final S3Utils s3;
    private final VehicleRepository vehicleRepository;
    private final BookingRepository bookingRepository;

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
        partnerDto.setOnboardingStatus(partnerDao.getOnboardingStatus());
        partnerDto.setUuid(partnerDao.getUuid());
        partnerDto.setPhoneNumber(partnerDao.getPhoneNumber());
        partnerDto.setProfileUrl(partnerDao.getProfileUrl());


        if (partnerDetails == null)
            return;

        partnerDto.setFirstName(partnerDetails.getFirstName());
        partnerDto.setLastName(partnerDetails.getLastName());

        result.setPartnerDetails(partnerDto);
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
        log.info("Action By Partner : {} {}", partner.getPunchId(), ApplicationConstants.GSON.toJson(actionDto));
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
            case SOC_ENTRY -> {
                partner = socEntry(partner, actionDto);
            }
            case ODOMETER_ENTRY -> {
                partner = odometerEntry(partner, actionDto);
            }
        }

        return PartnerDto.fromDao(partner);
    }

    private PartnerDao odometerEntry(PartnerDao partner, ActionDto actionDto) {
        return null;
    }

    private PartnerDao socEntry(PartnerDao partner, ActionDto actionDto) {
        if (PartnerStatus.VEHICLE_ASSIGNED.name().equals(partner.getStatus()))
            partner.setSubStatus(PartnerSubStatus.WAITING_FOR_ONLINE.name());

        if (PartnerStatus.RETURN_CHECKLIST.name().equals(partner.getStatus())) {
            partner.setSubStatus(PartnerSubStatus.CHECKLIST_PENDING.name());
        }


        VehicleDao vehicle = vehicleRepository.findById(partner.getVehicleId());
        VehicleStatsDto stats = VehicleStatsDto.builder().build();
        if (vehicle.getStats() != null) {
            stats = ApplicationConstants.GSON.fromJson(vehicle.getStats(), VehicleStatsDto.class);
        }
        stats.setSoc(StatsDto.builder()
                .manual(actionDto.getSoc())
                .calculated(actionDto.getSoc())
                .calculatedTimestamp(DateTime.now().getMillis())
                .timestamp(DateTime.now().getMillis())
                .build());
        vehicle.setStats(ApplicationConstants.GSON.toJson(stats));
        vehicleRepository.update(vehicle);

        VehicleViewDto viewDto = VehicleViewDto.fromDao(vehicle);
        if (viewDto != null) {
            viewDto.setStats(stats);
            partner.setVehicleDetails(ApplicationConstants.GSON.toJson(viewDto));
        }

        if (PartnerStatus.RETURN_CHECKLIST.name().equals(partner.getStatus())) {
            String plateNumber = vehicle.getPlateNumber();
            if (partner.getVehicleId() != null) {
                vehicle = vehicleRepository.findById(partner.getVehicleId());
                vehicle.setStatus(VehicleStatus.AVAILABLE.name());
                vehicleRepository.update(vehicle);
            }
            partner.setStatus(PartnerStatus.ON_DUTY.name());
            partner.setSubStatus(PartnerSubStatus.VEHICLE_NOT_ALLOTTED.name());
            partner.setVehicleDetails(null);
            partner.setVehicleId(null);

            if(partner.getPartnerDutyId()!=null){
                PartnerDutyDao partnerDutyDao = partnerDutyRepository.findById(partner.getPartnerDutyId());
                if(partnerDutyDao.getVehicles()!=null){
                    Type t = new TypeToken<List<PartnerDutyVehicleDetailsDto>>(){}.getType();
                    List<PartnerDutyVehicleDetailsDto> vehicles  = ApplicationConstants.GSON.fromJson(partnerDutyDao.getVehicles(),t);
                    if(!CollectionUtils.isEmpty(vehicles)){
                        vehicles = vehicles.stream().peek(vehicleDetailsDto->{
                            if(vehicleDetailsDto.getReleaseTime()==null && vehicleDetailsDto.getPlateNumber().equals(plateNumber)){
                                vehicleDetailsDto.setReleaseTime(DateTime.now());
                                vehicleDetailsDto.setStatus(PartnerDutyVehicleStatus.RELEASED.name());
                            }
                        }).toList();
                        partnerDutyDao.setVehicles(ApplicationConstants.GSON.toJson(vehicles));
                        partnerDutyRepository.update(partnerDutyDao);
                    }
                }
            }
        }
        partner = partnerRepository.update(partner);
        return partner;
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
        if (partnerViewDto != null) {
            partnerViewDto.setProfileUrl(partnerDetailDao.getProfileUrl());
            partner.setViewInfo(ApplicationConstants.GSON.toJson(partnerViewDto));
        }
        partnerRepository.update(partner);
        PartnerDetailDto result = PartnerDetailDto.builder().build();
        setPartnerDetails(result, partner, partnerDetailDao);
        setPartnerHomeLocation(result, partnerDetailDao.getHomeLocationId());
        return result;
    }

    private PartnerDao checkOut(PartnerDao partner, ActionDto actionDto) {
        PartnerShiftDao partnerShiftDao = partnerShiftRepository.findById(partner.getPartnerShiftId());
        if (partnerShiftDao == null)
            throw new ResponseException("Invalid action: Shift Details Incorrect");

        if (partnerShiftDao.getEstimatedEndTime() != null && partnerShiftDao.getEstimatedEndTime().isAfter(DateTime.now().plusMinutes(15)))
            throw new ResponseException("Checkout is allowed only 15 minutes from shift end");
        if (partnerShiftDao.getOutLocationId() == null)
            throw new ResponseException("Invalid action: Shift Details Incorrect No Location present");
        LocationDao expectedOutLocation = locationRepository.findById(partnerShiftDao.getOutLocationId());
        if (expectedOutLocation == null)
            throw new ResponseException("Invalid action: Shift Details Incorrect No Location present");

        validateLocationQr(actionDto.getQrString(), expectedOutLocation);
        validateLocationGps(actionDto.getLocation(), expectedOutLocation);

        partner.setStatus(PartnerStatus.OFF_DUTY.name());
        partner.setSubStatus(PartnerSubStatus.NO_DUTY.name());
        partner.setLocationDetails(null);
        partner.setLocationId(null);
        VehicleDao vehicle = null;
        if (partner.getVehicleId() != null) {
            vehicle = vehicleRepository.findById(partner.getVehicleId());
            vehicle.setStatus(VehicleStatus.AVAILABLE.name());
            vehicle =vehicleRepository.update(vehicle);

        }
        PartnerDutyDao currentDuty = partnerDutyRepository.findById(partner.getPartnerDutyId());
        if (currentDuty != null) {
            PartnerShiftDao shiftDao = partnerShiftRepository.findById(currentDuty.getPartnerShiftId());
            if (shiftDao != null)
                currentDuty.setActualDutyEndLocationDetails(shiftDao.getOutLocationDetails());
            currentDuty.setStatus(PartnerDutyStatus.COMPLETED.name());
            currentDuty.setActualDutyEndTime(DateTime.now());
            if(currentDuty.getVehicles()!=null){
                Type t = new TypeToken<List<PartnerDutyVehicleDetailsDto>>(){}.getType();
                List<PartnerDutyVehicleDetailsDto> vehicles  = ApplicationConstants.GSON.fromJson(currentDuty.getVehicles(),t);
                if(!CollectionUtils.isEmpty(vehicles) && vehicle !=null){
                    String plateNumber = vehicle.getPlateNumber();
                    vehicles = vehicles.stream().peek(vehicleDetailsDto->{
                        if(vehicleDetailsDto.getReleaseTime()==null && vehicleDetailsDto.getPlateNumber().equals(plateNumber)){
                            vehicleDetailsDto.setReleaseTime(DateTime.now());
                            vehicleDetailsDto.setStatus(PartnerDutyVehicleStatus.RELEASED.name());
                        }
                    }).toList();
                    currentDuty.setVehicles(ApplicationConstants.GSON.toJson(vehicles));
                }
            }
            partnerDutyRepository.update(currentDuty);
        }
        partner.setVehicleId(null);
        partner.setPartnerDutyId(null);
        partner.setPartnerShiftId(null);
        partner.setBookingId(null);

        if (partner.getPartnerDutyId() == null)
            partner.setDutyDetails(null);
        if (partner.getVehicleId() == null)
            partner.setVehicleDetails(null);
        if (partner.getBookingId() == null)
            partner.setBookingDetails(null);
        partner = partnerRepository.update(partner);
        return partner;
    }

    private PartnerDao goOffline(PartnerDao partner, ActionDto actionDto) {

        if (!PartnerStatus.ONLINE.name().equals(partner.getStatus()))
            throw new ResponseException("Partner Is not online yet");
        partner.setStatus(PartnerStatus.VEHICLE_ASSIGNED.name());
        partner.setSubStatus(PartnerSubStatus.WAITING_FOR_ONLINE.name());
        partner = partnerRepository.update(partner);
        return partner;
    }

    private PartnerDao complete(PartnerDao partner, ActionDto actionDto) {
        partner.setStatus(PartnerStatus.ONLINE.name());
        partner.setSubStatus(PartnerSubStatus.NO_BOOKING.name());
        if (partner.getBookingId() != null) {
            BookingDao bookingDao = bookingRepository.findById(partner.getBookingId());
            bookingDao.setStatus(BookingStatus.COMPLETED.name());
            bookingDao.setSubStatus(BookingSubStatus.COMPLETED.name());
            bookingRepository.update(bookingDao);
            partner.setBookingDetails(null);
            partner.setBookingId(null);
        }
        partner = partnerRepository.update(partner);
        return partner;
    }

    private PartnerDao end(PartnerDao partner, ActionDto actionDto) {
        partner.setStatus(PartnerStatus.ONLINE.name());
        partner.setSubStatus(PartnerSubStatus.NO_BOOKING.name());
        if (partner.getBookingId() != null) {
            BookingDao bookingDao = bookingRepository.findById(partner.getBookingId());
            bookingDao.setStatus(BookingStatus.COMPLETED.name());
            bookingDao.setSubStatus(BookingSubStatus.ENDED.name());
            BookingViewDto viewDto = BookingViewDto.fromDao(bookingDao);
            if (viewDto != null) {
                viewDto.setStatus(bookingDao.getStatus());
                viewDto.setSubStatus(bookingDao.getSubStatus());
            }
            bookingDao.setViewInfo(ApplicationConstants.GSON.toJson(viewDto));
            bookingRepository.update(bookingDao);
            partner.setBookingDetails(null);
            partner.setBookingId(null);
        }

        partner = partnerRepository.update(partner);
        return partner;
    }

    private PartnerDao start(PartnerDao partner, ActionDto actionDto) {
        partner.setStatus(PartnerStatus.ON_BOOKING.name());
        partner.setSubStatus(PartnerSubStatus.STARTED.name());
        partner = partnerRepository.update(partner);
        if (partner.getBookingId() != null) {
            BookingDao bookingDao = bookingRepository.findById(partner.getBookingId());
            bookingDao.setStatus(BookingStatus.IN_PROGRESS.name());
            bookingDao.setSubStatus(BookingSubStatus.STARTED.name());
            BookingViewDto viewDto = BookingViewDto.fromDao(bookingDao);
            if (viewDto != null) {
                viewDto.setStatus(bookingDao.getStatus());
                viewDto.setSubStatus(bookingDao.getSubStatus());
            }
            bookingDao.setViewInfo(ApplicationConstants.GSON.toJson(viewDto));
            bookingRepository.update(bookingDao);
        }
        return partner;
    }

    private PartnerDao arrive(PartnerDao partner, ActionDto actionDto) {
        partner.setStatus(PartnerStatus.ON_BOOKING.name());
        partner.setSubStatus(PartnerSubStatus.ARRIVED.name());
        partner = partnerRepository.update(partner);
        if (partner.getBookingId() != null) {
            BookingDao bookingDao = bookingRepository.findById(partner.getBookingId());
            bookingDao.setStatus(BookingStatus.IN_PROGRESS.name());
            bookingDao.setSubStatus(BookingSubStatus.ARRIVED.name());
            BookingViewDto viewDto = BookingViewDto.fromDao(bookingDao);
            if (viewDto != null) {
                viewDto.setStatus(bookingDao.getStatus());
                viewDto.setSubStatus(bookingDao.getSubStatus());
            }
            bookingDao.setViewInfo(ApplicationConstants.GSON.toJson(viewDto));
            bookingRepository.update(bookingDao);
        }
        return partner;
    }

    private PartnerDao enroute(PartnerDao partner, ActionDto actionDto) {
        partner.setStatus(PartnerStatus.ON_BOOKING.name());
        partner.setSubStatus(PartnerSubStatus.ENROUTE.name());
        partner = partnerRepository.update(partner);
        if (partner.getBookingId() != null) {
            BookingDao bookingDao = bookingRepository.findById(partner.getBookingId());
            bookingDao.setStatus(BookingStatus.IN_PROGRESS.name());
            bookingDao.setSubStatus(BookingSubStatus.ENROUTE.name());
            BookingViewDto viewDto = BookingViewDto.fromDao(bookingDao);
            if (viewDto != null) {
                viewDto.setStatus(bookingDao.getStatus());
                viewDto.setSubStatus(bookingDao.getSubStatus());
            }
            bookingDao.setViewInfo(ApplicationConstants.GSON.toJson(viewDto));
            bookingRepository.update(bookingDao);
        }
        return partner;
    }

    private PartnerDao unPause(PartnerDao partner, ActionDto actionDto) {
        partner.setStatus(PartnerStatus.ONLINE.name());
        partner.setSubStatus(PartnerSubStatus.NO_BOOKING.name());
        partner = partnerRepository.update(partner);
        return partner;
    }

    private PartnerDao pause(PartnerDao partner, ActionDto actionDto) {
        partner.setStatus(PartnerStatus.ONLINE.name());
        partner.setSubStatus(PartnerSubStatus.PAUSE.name());
        partner = partnerRepository.update(partner);
        return partner;
    }

    private PartnerDao goOnline(PartnerDao partner, ActionDto actionDto) {
        partner.setStatus(PartnerStatus.ONLINE.name());
        partner.setSubStatus(PartnerSubStatus.NO_BOOKING.name());
        partner.setBookingId(null);
        partner.setBookingDetails(null);
        partner = partnerRepository.update(partner);
        return partner;
    }

    private PartnerDao submitChecklist(PartnerDao partner, ActionDto actionDto) {
        if (PartnerStatus.CHECKLIST.name().equals(partner.getStatus())) {
            partner.setStatus(PartnerStatus.VEHICLE_ASSIGNED.name());
            partner.setSubStatus(PartnerSubStatus.SOC_ENTRY.name());
            partner = partnerRepository.update(partner);
        } else if (PartnerStatus.RETURN_CHECKLIST.name().equals(partner.getStatus())) {

            if (partner.getVehicleId() != null) {
                VehicleDao vehicle = vehicleRepository.findById(partner.getVehicleId());
                vehicle.setStatus(VehicleStatus.AVAILABLE.name());
                vehicleRepository.update(vehicle);
            }
            partner.setStatus(PartnerStatus.ON_DUTY.name());
            partner.setSubStatus(PartnerSubStatus.VEHICLE_NOT_ALLOTTED.name());
            partner.setVehicleDetails(null);
            partner.setVehicleId(null);
            partner = partnerRepository.update(partner);
        }
        return partner;
    }

    private PartnerDao selectVehicle(PartnerDao partner, ActionDto actionDto) {
//        partner.setStatus(PartnerStatus.CHECKLIST.name());
//        partner.setSubStatus(PartnerSubStatus.CHECKLIST_PENDING.name());
        partner.setStatus(PartnerStatus.VEHICLE_ASSIGNED.name());
        partner.setSubStatus(PartnerSubStatus.SOC_ENTRY.name());
        partner = partnerRepository.update(partner);
        return partner;
    }

    private PartnerDao returnVehicle(PartnerDao partner, ActionDto actionDto) {
        partner.setStatus(PartnerStatus.RETURN_CHECKLIST.name());
        partner.setSubStatus(PartnerSubStatus.SOC_ENTRY.name());
        partner = partnerRepository.update(partner);
        return partner;
    }

    private PartnerDao checkin(PartnerDao partner, ActionDto actionDto) {

        log.info("Partner Shift Id : {}", partner.getPartnerShiftId());
        PartnerShiftDao partnerShiftDao = partnerShiftRepository.findById(partner.getPartnerShiftId());
        if (partnerShiftDao == null)
            throw new ResponseException("Invalid action: Shift Details Incorrect");

        if (partnerShiftDao.getEstimatedStartTime() != null && partnerShiftDao.getEstimatedStartTime().isAfter(DateTime.now().plusMinutes(15)))
            throw new ResponseException("Checkin is allowed only 15 minutes from shift start");
        if (partnerShiftDao.getInLocationDetails() == null)
            throw new ResponseException("Invalid action: Shift Details Incorrect No Location present");
        LocationDao expectedInLocation = locationRepository.findById(partnerShiftDao.getInLocationId());
        if (expectedInLocation == null)
            throw new ResponseException("Invalid action: Shift Details Incorrect No Location present");

        validateLocationQr(actionDto.getQrString(), expectedInLocation);
        validateLocationGps(actionDto.getLocation(), expectedInLocation);


        PartnerDutyDao newDuty = new PartnerDutyDao();

        newDuty.setPartnerId(partner.getId());
        newDuty.setStatus(PartnerDutyStatus.IN_PROGRESS.name());
        newDuty.setPartnerShiftId(partnerShiftDao.getId());

        newDuty.setActualDutyStartTime(DateTime.now());
        newDuty.setActualDutyStartLocationDetails(partnerShiftDao.getInLocationDetails());

        newDuty.setPlannedDutyStartTime(partnerShiftDao.getEstimatedStartTime());
        newDuty.setPlannedOnlineTime(partnerShiftDao.getEstimatedOnlineTime());
        newDuty.setPlannedDutyEndTime(partnerShiftDao.getEstimatedEndTime());

        newDuty.setPlannedDutyStartLocationDetails(partnerShiftDao.getInLocationDetails());
        newDuty.setPlannedOnlineLocationDetails(partnerShiftDao.getOnlineLocationDetails());
        newDuty.setPlannedDutyEndLocationDetails(partnerShiftDao.getOutLocationDetails());

        newDuty.setPlannedDutyStartLocationId(partnerShiftDao.getInLocationId());
        newDuty.setPlannedOnlineLocationId(partnerShiftDao.getOnlineLocationId());
        newDuty.setPlannedDutyEndLocationId(partnerShiftDao.getOutLocationId());
        newDuty.setMaxOvertimeCalculationTime(partnerShiftDao.getEstimatedEndTime());
        newDuty = partnerDutyRepository.save(newDuty);

        partner.setLocationDetails(partnerShiftDao.getInLocationDetails());
        partner.setDutyDetails(ApplicationConstants.GSON.toJson(PartnerDutyDto.fromDao(newDuty, PartnerViewDto.fromDao(partner), partnerShiftDao)));
        partner.setPartnerDutyId(newDuty.getId());
        partner.setStatus(PartnerStatus.ON_DUTY.name());
        partner.setSubStatus(PartnerSubStatus.VEHICLE_NOT_ALLOTTED.name());
        partner = partnerRepository.update(partner);

        partnerShiftDao.setStatus(PartnerShiftStatus.IN_PROGRESS.name());
        partnerShiftDao.setSubStatus(PartnerShiftSubStatus.PRESENT.name());
        partnerShiftRepository.update(partnerShiftDao);

        return partner;
    }

    private void validateLocationGps(LatLongDto location, LocationDao expectedInLocation) {
        if (location == null)
            throw new ResponseException("Invalid Location.Please turn on your gps.");
        if (location.getLongitude() == null || location.getLatitude() == null)
            throw new ResponseException("Invalid Location.Please turn on your gps.");
        double distance = DistanceUtils.calculateDistance(location, LatLongDto.builder().latitude(expectedInLocation.getLatitude()).longitude(expectedInLocation.getLongitude()).build());
        if (distance > 500.0)
            throw new ResponseException("Invalid Location.You are far way from station.");
    }

    private void validateLocationQr(String qrString, LocationDao expectedInLocation) {
        try {
            QrValueDto valueDto = ApplicationConstants.GSON.fromJson(qrString, QrValueDto.class);
            if (valueDto == null) {
                throw new ResponseException("Invalid QR Code.");
            }
            if (!valueDto.getUuid().equals(expectedInLocation.getUuid()))
                throw new ResponseException("Invalid QR Code.Please scan qr of correct hub");
        } catch (ResponseException e) {
            throw e;
        } catch (Exception e) {
            log.info("Error in parsing qr", e);
            throw new ResponseException("Invalid QR Code.");
        }
    }
}

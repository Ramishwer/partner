package com.goev.partner.service.vehicle.impl;


import com.goev.lib.exceptions.ResponseException;
import com.goev.partner.constant.ApplicationConstants;
import com.goev.partner.dao.asset.AssetDao;
import com.goev.partner.dao.vehicle.asset.VehicleAssetMappingDao;
import com.goev.partner.dao.vehicle.detail.VehicleDao;
import com.goev.partner.dao.vehicle.transfer.VehicleAssetTransferDetailDao;
import com.goev.partner.dao.vehicle.transfer.VehicleTransferDetailDao;
import com.goev.partner.dto.asset.AssetDto;
import com.goev.partner.dto.common.PaginatedResponseDto;
import com.goev.partner.dto.common.QrValueDto;
import com.goev.partner.dto.vehicle.VehicleViewDto;
import com.goev.partner.dto.vehicle.transfer.VehicleAssetTransferDetailDto;
import com.goev.partner.dto.vehicle.transfer.VehicleTransferDto;
import com.goev.partner.enums.vehicle.AssetStatus;
import com.goev.partner.repository.asset.AssetRepository;
import com.goev.partner.repository.vehicle.asset.VehicleAssetMappingRepository;
import com.goev.partner.repository.vehicle.detail.VehicleRepository;
import com.goev.partner.repository.vehicle.transfer.VehicleAssetTransferDetailRepository;
import com.goev.partner.repository.vehicle.transfer.VehicleTransferDetailRepository;
import com.goev.partner.service.vehicle.VehicleTransferService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;


@Slf4j
@Service
@AllArgsConstructor
public class VehicleTransferServiceImpl implements VehicleTransferService {

    private final VehicleRepository vehicleRepository;
    private final VehicleTransferDetailRepository vehicleTransferDetailRepository;
    private final VehicleAssetMappingRepository vehicleAssetMappingRepository;
    private final VehicleAssetTransferDetailRepository vehicleAssetTransferDetailRepository;
    private final AssetRepository assetRepository;


    @Override
    public VehicleTransferDto createVehicleTransfer(String vehicleUUID, VehicleTransferDto vehicleTransferDto) {
        VehicleDao vehicle = vehicleRepository.findByUUID(vehicleUUID);
        if (vehicle == null)
            throw new ResponseException("No vehicle found for uuid:" + vehicleUUID);

        VehicleTransferDetailDao detailDao = vehicleTransferDetailRepository.save(VehicleTransferDetailDao.fromDto(vehicleTransferDto, vehicle.getId()));
        if (detailDao == null)
            throw new ResponseException("Error in creating vehicle transfer");
        return VehicleTransferDto.fromDao(detailDao, VehicleViewDto.fromDao(vehicle));
    }

    @Override
    public PaginatedResponseDto<VehicleAssetTransferDetailDto> getVehicleAssetsForTransfer(String vehicleUUID, String vehicleTransferUUID) {
        VehicleDao vehicle = vehicleRepository.findByUUID(vehicleUUID);
        if (vehicle == null)
            throw new ResponseException("No vehicle found for uuid:" + vehicleUUID);

        VehicleTransferDetailDao detailDao = vehicleTransferDetailRepository.findByUUID(vehicleTransferUUID);
        if (detailDao == null)
            throw new ResponseException("No vehicle transfer detail found for uuid:" + vehicleTransferUUID);

//        List<VehicleAssetMappingDao> assetMappingDaoList = vehicleAssetMappingRepository.findAllByVehicleId(vehicle.getId());
//
//        if (CollectionUtils.isEmpty(assetMappingDaoList))
//            return new PaginatedResponseDto<>();


        PaginatedResponseDto<VehicleAssetTransferDetailDto> result = new PaginatedResponseDto<>();
        List<VehicleAssetTransferDetailDto> assetList = new ArrayList<>();
//        for (VehicleAssetMappingDao mappingDao : assetMappingDaoList) {
//            AssetDao asset = assetRepository.findById(mappingDao.getAssetId());
//            if (asset == null)
//                continue;
//            VehicleAssetTransferDetailDao assetTransferDetailDao = new VehicleAssetTransferDetailDao();
//            assetTransferDetailDao.setVehicleTransferId(detailDao.getId());
//            assetTransferDetailDao.setVehicleId(vehicle.getId());
//            assetTransferDetailDao.setAssetId(asset.getId());
//            assetTransferDetailDao = vehicleAssetTransferDetailRepository.save(assetTransferDetailDao);
//            assetList.add(VehicleAssetTransferDetailDto.fromDao(assetTransferDetailDao, AssetDto.fromDao(asset)));
//        }


        for (AssetDto assetDto : ApplicationConstants.MANDATORY_ASSETS ) {
            VehicleAssetTransferDetailDao assetTransferDetailDao = new VehicleAssetTransferDetailDao();
            assetTransferDetailDao.setVehicleTransferId(detailDao.getId());
            assetTransferDetailDao.setVehicleId(vehicle.getId());
            assetTransferDetailDao.setStatus(AssetStatus.PENDING.name());
            assetTransferDetailDao = vehicleAssetTransferDetailRepository.save(assetTransferDetailDao);
            assetList.add(VehicleAssetTransferDetailDto.fromDao(assetTransferDetailDao, assetDto));
        }

        result.setElements(assetList);
        return result;
    }

    @Override
    public VehicleTransferDto getVehicleTransferDetails(String vehicleUUID, String vehicleTransferUUID) {
        VehicleDao vehicle = vehicleRepository.findByUUID(vehicleUUID);
        if (vehicle == null)
            throw new ResponseException("No vehicle found for uuid:" + vehicleUUID);

        VehicleTransferDetailDao detailDao = vehicleTransferDetailRepository.findByUUID(vehicleTransferUUID);
        if (detailDao == null)
            throw new ResponseException("No vehicle transfer detail found for uuid:" + vehicleTransferUUID);
        return VehicleTransferDto.fromDao(detailDao, VehicleViewDto.fromDao(vehicle));
    }

    @Override
    public VehicleTransferDto updateVehicleTransfer(String vehicleUUID, String vehicleTransferUUID, VehicleTransferDto vehicleTransferDto) {
        VehicleDao vehicle = vehicleRepository.findByUUID(vehicleUUID);
        if (vehicle == null)
            throw new ResponseException("No vehicle found for uuid:" + vehicleUUID);
        VehicleTransferDetailDao detailDao = vehicleTransferDetailRepository.findByUUID(vehicleTransferUUID);

        if (detailDao == null)
            throw new ResponseException("No vehicle transfer detail found for uuid:" + vehicleTransferUUID);
        VehicleTransferDetailDao newDetailDao = VehicleTransferDetailDao.fromDto(vehicleTransferDto, vehicle.getId());
        newDetailDao.setId(detailDao.getId());
        newDetailDao.setUuid(detailDao.getUuid());
        newDetailDao.setCreatedBy(detailDao.getCreatedBy());
        newDetailDao.setUpdatedBy(detailDao.getUpdatedBy());
        newDetailDao.setCreatedOn(detailDao.getCreatedOn());
        newDetailDao.setUpdatedOn(detailDao.getUpdatedOn());
        newDetailDao.setIsActive(detailDao.getIsActive());
        newDetailDao.setState(detailDao.getState());
        newDetailDao.setApiSource(detailDao.getApiSource());
        newDetailDao.setNotes(detailDao.getNotes());
        detailDao = vehicleTransferDetailRepository.update(newDetailDao);
        if (detailDao == null)
            throw new ResponseException("Error in creating vehicle transfer");
        return VehicleTransferDto.fromDao(detailDao, VehicleViewDto.fromDao(vehicle));
    }

    @Override
    public VehicleAssetTransferDetailDto saveStatusForAssetForQrString(String vehicleUUID, String vehicleTransferUUID, String assetTransferDetailUUID, String status, String assetQrString) {
        VehicleDao vehicle = vehicleRepository.findByUUID(vehicleUUID);
        if (vehicle == null)
            throw new ResponseException("No vehicle found for uuid:" + vehicleUUID);

        VehicleTransferDetailDao detailDao = vehicleTransferDetailRepository.findByUUID(vehicleTransferUUID);
        if (detailDao == null)
            throw new ResponseException("No vehicle transfer detail found for uuid:" + vehicleTransferUUID);

        VehicleAssetTransferDetailDao assetTransferDetailDao = vehicleAssetTransferDetailRepository.findByUUID(assetTransferDetailUUID);
        if (assetTransferDetailDao == null)
            throw new ResponseException("No vehicle asset transfer detail found for uuid:" + assetTransferDetailUUID);

        if (AssetStatus.ABSENT.name().equals(status)) {
            assetTransferDetailDao.setStatus(AssetStatus.ABSENT.name());
            vehicleAssetTransferDetailRepository.update(assetTransferDetailDao);
            return VehicleAssetTransferDetailDto.fromDao(assetTransferDetailDao, null);
        } else {

            String parsedUUID = getParsedQrCode(assetQrString);

            if (parsedUUID == null)
                throw new ResponseException("Invalid Qr Code for the item");
            AssetDao assetDao = assetRepository.findByUUID(parsedUUID);
            if (assetDao == null)
                throw new ResponseException("Invalid Qr Code for the item");
//            if (!assetDao.getId().equals(assetTransferDetailDao.getAssetId()))
//                throw new ResponseException("Invalid Qr Code for the item");

            assetTransferDetailDao.setAssetId(assetDao.getId());
            assetTransferDetailDao.setStatus(AssetStatus.PRESENT.name());
            vehicleAssetTransferDetailRepository.update(assetTransferDetailDao);
            return VehicleAssetTransferDetailDto.fromDao(assetTransferDetailDao, AssetDto.fromDao(assetDao));
        }


    }

    private String getParsedQrCode(String assetQrString) {
        try {
            QrValueDto qrValueDto = ApplicationConstants.GSON.fromJson(assetQrString, QrValueDto.class);
            return qrValueDto.getUuid();
        } catch (Exception e) {
            log.error("Error in parsing qr", e);
        }
        return null;
    }
}

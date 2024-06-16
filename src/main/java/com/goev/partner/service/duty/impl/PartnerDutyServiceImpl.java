package com.goev.partner.service.duty.impl;

import com.goev.lib.exceptions.ResponseException;
import com.goev.partner.dao.partner.detail.PartnerDao;
import com.goev.partner.dao.partner.duty.PartnerDutyDao;
import com.goev.partner.dao.partner.duty.PartnerShiftDao;
import com.goev.partner.dto.common.PageDto;
import com.goev.partner.dto.common.PaginatedResponseDto;
import com.goev.partner.dto.partner.PartnerViewDto;
import com.goev.partner.dto.partner.duty.PartnerDutyDto;
import com.goev.partner.repository.partner.detail.PartnerRepository;
import com.goev.partner.repository.partner.duty.PartnerDutyRepository;
import com.goev.partner.repository.partner.duty.PartnerShiftRepository;
import com.goev.partner.service.duty.PartnerDutyService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class PartnerDutyServiceImpl implements PartnerDutyService {

    private final PartnerRepository partnerRepository;
    private final PartnerDutyRepository partnerDutyRepository;
    private final PartnerShiftRepository partnerShiftRepository;

    @Override
    public PaginatedResponseDto<PartnerDutyDto> getDutiesForPartner(String partnerUUID, PageDto page) {
        PartnerDao partnerDao = partnerRepository.findByUUID(partnerUUID);
        if (partnerDao == null)
            throw new ResponseException("No partner found for id :" + partnerUUID);

        PaginatedResponseDto<PartnerDutyDto> result = PaginatedResponseDto.<PartnerDutyDto>builder().elements(new ArrayList<>()).build();
        List<PartnerDutyDao> duties = partnerDutyRepository.findAllByPartnerId(partnerDao.getId(), page);
        if (CollectionUtils.isEmpty(duties))
            return result;
        for (PartnerDutyDao dutyDao : duties) {
            result.getElements().add(getPartnerDutyDto(dutyDao, partnerDao));
        }
        return result;
    }

    public PartnerDutyDto getPartnerDutyDto(PartnerDutyDao dutyDao, PartnerDao partnerDao) {

        PartnerShiftDao shift = null;
        if (dutyDao.getPartnerShiftId() != null)
            shift = partnerShiftRepository.findById(dutyDao.getPartnerShiftId());

        return PartnerDutyDto.fromDao(dutyDao, PartnerViewDto.fromDao(partnerDao), shift);
    }

    @Override
    public PartnerDutyDto getDutyDetails(String partnerUUID, String dutyUUID) {

        PartnerDao partnerDao = partnerRepository.findByUUID(partnerUUID);
        if (partnerDao == null)
            throw new ResponseException("No partner found for id :" + partnerUUID);
        PartnerDutyDao dutyDao = partnerDutyRepository.findByUUID(dutyUUID);

        if (dutyDao == null)
            throw new ResponseException("No duty found for id :" + dutyUUID);

        return getPartnerDutyDto(dutyDao, partnerDao);
    }
}

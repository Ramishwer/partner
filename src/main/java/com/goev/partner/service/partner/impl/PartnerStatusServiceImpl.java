package com.goev.partner.service.partner.impl;

import com.goev.lib.exceptions.ResponseException;
import com.goev.lib.utilities.ApplicationContext;
import com.goev.partner.dao.partner.detail.PartnerDao;
import com.goev.partner.dto.partner.status.ActionDto;
import com.goev.partner.dto.partner.status.PartnerStatusDto;
import com.goev.partner.enums.partner.PartnerStatus;
import com.goev.partner.repository.partner.detail.PartnerRepository;
import com.goev.partner.service.partner.PartnerStatusService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class PartnerStatusServiceImpl implements PartnerStatusService {
    private final PartnerRepository partnerRepository;
    @Override
    public PartnerStatusDto getStatusForPartner(String partnerUUID) {
        PartnerDao partner = partnerRepository.findByAuthUUID(ApplicationContext.getAuthUUID());
        if (partner == null)
            throw new ResponseException("No partner found for Id :" + partnerUUID);

        PartnerStatusDto result = PartnerStatusDto.builder()
                .subStatus(partner.getSubStatus())
                .status(partner.getStatus())
                .onboardingStatus(partner.getOnboardingStatus())
                .build();
        return result;
    }

    @Override
    public PartnerStatusDto savePartnerStatus(String partnerUUID, ActionDto actionDto) {
        return null;
    }
}

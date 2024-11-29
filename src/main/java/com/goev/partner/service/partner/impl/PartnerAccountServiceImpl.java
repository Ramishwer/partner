package com.goev.partner.service.partner.impl;


import com.goev.lib.exceptions.ResponseException;
import com.goev.partner.dao.partner.detail.PartnerAccountDetailDao;
import com.goev.partner.dao.partner.detail.PartnerDao;
import com.goev.partner.dto.partner.detail.PartnerAccountDto;
import com.goev.partner.repository.partner.detail.PartnerAccountDetailRepository;
import com.goev.partner.repository.partner.detail.PartnerRepository;
import com.goev.partner.service.partner.PartnerAccountService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class PartnerAccountServiceImpl implements PartnerAccountService {
    private final PartnerAccountDetailRepository partnerAccountDetailRepository;
    private final PartnerRepository partnerRepository;

    @Override
    public PartnerAccountDto createAccount(String partnerUUID, PartnerAccountDto partnerAccountDto) {

        PartnerDao partner = partnerRepository.findByUUID(partnerUUID);
        if (partner == null)
            throw new ResponseException("No partner found for Id :" + partnerUUID);

        PartnerAccountDetailDao partnerAccountDetailDao = new PartnerAccountDetailDao();
        partnerAccountDetailDao.setAccountNumber(partnerAccountDto.getAccountNumber());
        partnerAccountDetailDao.setIfscCode(partnerAccountDto.getIfscCode());
        partnerAccountDetailDao.setAccountHolderName(partnerAccountDto.getAccountHolderName());

        partnerAccountDetailDao.setPartnerId(partner.getId());
        partnerAccountDetailDao = partnerAccountDetailRepository.save(partnerAccountDetailDao);
        if (partnerAccountDetailDao == null)
            throw new ResponseException("Error in saving partner account");
        return PartnerAccountDto.builder()
                .ifscCode(partnerAccountDetailDao.getIfscCode())
                .accountHolderName(partnerAccountDetailDao.getAccountHolderName())
                .accountNumber(partnerAccountDetailDao.getAccountNumber())
                .uuid(partnerAccountDetailDao.getUuid())
                .build();
    }


}
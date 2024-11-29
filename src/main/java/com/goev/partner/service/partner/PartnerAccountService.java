package com.goev.partner.service.partner;


import com.goev.partner.dto.partner.detail.PartnerAccountDto;

public interface PartnerAccountService {
    PartnerAccountDto createAccount(String partnerUUID, PartnerAccountDto partnerAccountDto);

}

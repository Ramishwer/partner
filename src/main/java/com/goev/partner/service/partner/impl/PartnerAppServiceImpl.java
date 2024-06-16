package com.goev.partner.service.partner.impl;

import com.goev.partner.dao.partner.app.PartnerAppSupportedLanguageDao;
import com.goev.partner.dao.system.property.SystemPropertyDao;
import com.goev.partner.dto.partner.app.AppPropertyDto;
import com.goev.partner.dto.partner.app.AppSupportedLanguageDto;
import com.goev.partner.repository.partner.app.PartnerAppSupportedLanguageRepository;
import com.goev.partner.repository.system.property.SystemPropertyRepository;
import com.goev.partner.service.partner.PartnerAppService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@AllArgsConstructor
public class PartnerAppServiceImpl implements PartnerAppService {
    private final PartnerAppSupportedLanguageRepository partnerAppSupportedLanguageRepository;
    private final SystemPropertyRepository systemPropertyRepository;

    @Override
    public AppPropertyDto getAppProperties() {
        Map<String, SystemPropertyDao> propertyMap = systemPropertyRepository.getPropertyMap();
        List<PartnerAppSupportedLanguageDao> languageDaos = partnerAppSupportedLanguageRepository.findAllActive();
        List<AppSupportedLanguageDto> supportedLanguages = new ArrayList<>();
        languageDaos.forEach(x -> {
            supportedLanguages.add(AppSupportedLanguageDto.builder()
                    .languageCode(x.getLanguageCode())
                    .name(x.getName())
                    .url(x.getS3Key())
                    .build());
        });

        return AppPropertyDto.builder()
                .currentAppVersion(propertyMap.getOrDefault("CURRENT_APP_VERSION", new SystemPropertyDao()).getPropertyValue())
                .minimumAppVersion(propertyMap.getOrDefault("MINIMUM_APP_VERSION", new SystemPropertyDao()).getPropertyValue())
                .supportedLanguages(supportedLanguages).build();
    }
}

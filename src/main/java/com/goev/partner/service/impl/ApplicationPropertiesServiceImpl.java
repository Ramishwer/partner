package com.goev.partner.service.impl;

import com.goev.partner.dto.application.ApplicationPropertiesDto;
import com.goev.partner.service.ApplicationPropertiesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ApplicationPropertiesServiceImpl implements ApplicationPropertiesService {
    @Override
    public ApplicationPropertiesDto getApplicationProperties() {
        return ApplicationPropertiesDto.builder().currentAppVersion("1.0.0").minimumAppVersion("0.0.0").build();
    }
}

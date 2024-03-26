package com.goev.partner.repository.system.properties;

import com.goev.partner.dao.system.properties.SystemPropertiesDao;

import java.util.Map;

public interface SystemPropertiesRepository {
    Map<String, SystemPropertiesDao> getPropertyMap();
}

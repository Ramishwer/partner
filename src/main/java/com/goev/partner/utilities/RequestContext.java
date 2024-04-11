package com.goev.partner.utilities;

import com.goev.partner.dao.partner.detail.PartnerSessionDao;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class RequestContext {
    private RequestContext() {
    }

    public static String getAccessToken() {
        ServletRequestAttributes requestAttributes = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes());
        String clientSecret = null;
        if (requestAttributes != null) {
            HttpServletRequest request = requestAttributes.getRequest();
            if (request.getHeader("Authorization") != null)
                clientSecret = request.getHeader("Authorization");
        }
        return clientSecret;
    }

    public static String getSessionUUID() {
        ServletRequestAttributes requestAttributes = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes());
        String sessionUUID = null;
        if (requestAttributes != null) {
            HttpServletRequest request = requestAttributes.getRequest();
            if (request.getHeader("Session-UUID") != null)
                sessionUUID = request.getHeader("Session-UUID");
        }
        return sessionUUID;
    }

    public static String getDeviceUUID() {
        ServletRequestAttributes requestAttributes = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes());
        String deviceUUID = null;
        if (requestAttributes != null) {
            HttpServletRequest request = requestAttributes.getRequest();
            if (request.getHeader("Device-UUID") != null)
                deviceUUID = request.getHeader("Device-UUID");
        }
        return deviceUUID;
    }

    public static String getPlatform() {
        ServletRequestAttributes requestAttributes = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes());
        String platform = null;
        if (requestAttributes != null) {
            HttpServletRequest request = requestAttributes.getRequest();
            if (request.getHeader("Platform") != null)
                platform = request.getHeader("Platform");
        }
        return platform;
    }

    public static String getVersion() {
        ServletRequestAttributes requestAttributes = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes());
        String version = null;
        if (requestAttributes != null) {
            HttpServletRequest request = requestAttributes.getRequest();
            if (request.getHeader("Version") != null)
                version = request.getHeader("Version");
        }
        return version;
    }

    public static String getLanguage() {
        ServletRequestAttributes requestAttributes = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes());
        String language = null;
        if (requestAttributes != null) {
            HttpServletRequest request = requestAttributes.getRequest();
            if (request.getHeader("Language") != null)
                language = request.getHeader("Language");
        }
        return language;
    }


    public static String getRefreshToken() {
        ServletRequestAttributes requestAttributes = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes());
        String clientSecret = null;
        if (requestAttributes != null) {
            HttpServletRequest request = requestAttributes.getRequest();
            if (request.getHeader("Refresh-Token") != null)
                clientSecret = request.getHeader("Refresh-Token");
        }
        return clientSecret;
    }

    public static PartnerSessionDao getPartnerSession() {
        ServletRequestAttributes requestAttributes = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes());
        PartnerSessionDao partnerSessionDao = null;
        if (requestAttributes != null) {
            HttpServletRequest request = requestAttributes.getRequest();
            if (request.getAttribute("partnerSession") != null)
                partnerSessionDao =(PartnerSessionDao) request.getAttribute("partnerSession");
        }
        return partnerSessionDao;
    }
}


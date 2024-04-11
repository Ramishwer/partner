package com.goev.partner.dto.partner.detail;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class PartnerDeviceDto {
    private String fcmToken;
    private String imei1;
    private String imei2;
    private String screenHeight;
    private String screenWidth;
    private String screenDpi;
    private String appVersion;
    private String platform;
    private String brand;
    private String model;
    private String carrier;
    private String osVersion;
    private String gms;
    private String manufacturer;
    private String city;
    private String distinctId;
    private String uuid;
}

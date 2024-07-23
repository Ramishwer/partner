package com.goev.partner.constant;

import com.goev.lib.utilities.GsonDateTimeSerializer;
import com.goev.partner.dto.asset.AssetDto;
import com.goev.partner.utilities.ConstantUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Slf4j
@AllArgsConstructor
public class ApplicationConstants {
    public static final Gson GSON = new GsonBuilder().registerTypeAdapter(DateTime.class, new GsonDateTimeSerializer()).create();
    public static String TIMER_URL;
    public static String AWS_ACCESS_KEY_SECRET;
    public static String AWS_ACCESS_KEY_ID;
    public static String APPLICATION_ID;
    public static String CLIENT_ID;
    public static String CLIENT_SECRET;
    public static String USER_NAME;
    public static String USER_PASSWORD;
    public static String S3_BUCKET_NAME;
    public static String AUTH_URL;
    public static String CENTRAL_URL;
    public static String CREDENTIAL_TYPE_NAME;
    public static String CREDENTIAL_TYPE_UUID;

    private final ConstantUtils constantUtils;

    public static List<AssetDto> MANDATORY_ASSETS;

    @PostConstruct
    void init() throws IllegalAccessException {
        constantUtils.configurationOfConstantsFromDataBase(this);


        MANDATORY_ASSETS = new ArrayList<>();

        MANDATORY_ASSETS.add(AssetDto.builder().assetName("TOOLBOX").build());
        MANDATORY_ASSETS.add(AssetDto.builder().assetName("SLOW-CHARGER").build());
        MANDATORY_ASSETS.add(AssetDto.builder().assetName("STEPNEY").build());

    }

}

package com.goev.partner.constant;

import com.goev.lib.utilities.GsonDateTimeSerializer;
import com.goev.partner.utilities.ConstantUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
@AllArgsConstructor
public class ApplicationConstants {
    public static final Gson GSON = new GsonBuilder().registerTypeAdapter(DateTime.class, new GsonDateTimeSerializer()).create();
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


    public static final Map<String,String> assignmentMap = new HashMap<>();

    @PostConstruct
    void init() throws IllegalAccessException {
        constantUtils.configurationOfConstantsFromDataBase(this);

        assignmentMap.put("GOEV-2e523-43673","TEST0001");

        assignmentMap.put("GOEV-1ef0a-80684","DL51EV7305");

        assignmentMap.put("GOEV-90e6c-80273","DL51EV7320");

        assignmentMap.put("GOEV-8584f-28488","DL51EV7432");

        assignmentMap.put("GOEV-fd20f-80404","DL51EV7477");

        assignmentMap.put("GOEV-89fbf-44864","DL51EV7555");

        assignmentMap.put("GOEV-1c9ff-63223","DL51EV7881");
    }

}

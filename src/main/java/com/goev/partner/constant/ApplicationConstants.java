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


    public static final Map<Integer,String> assignmentMap = new HashMap<>();

    @PostConstruct
    void init() throws IllegalAccessException {
        constantUtils.configurationOfConstantsFromDataBase(this);

        assignmentMap.put(8,"DL51EV1878");
        assignmentMap.put(11,"DL51GD5994");
        assignmentMap.put(41,"DL51GD6026");
        assignmentMap.put(20,"DL51EV6298");
        assignmentMap.put(16,"DL51GD6621");
        assignmentMap.put(25,"DL51GD6639");
        assignmentMap.put(94,"DL51EV7305");
        assignmentMap.put(3,"DL51EV7320");
        assignmentMap.put(39,"DL51EV7411");
        assignmentMap.put(5,"DL51EV7421");
        assignmentMap.put(17,"DL51EV7432");
        assignmentMap.put(32,"DL51EV7455");
        assignmentMap.put(7,"DL51EV7477");
        assignmentMap.put(9,"DL51EV7479");
        assignmentMap.put(22,"DL51EV7555");
        assignmentMap.put(2,"DL51EV7631");
        assignmentMap.put(4,"DL51EV7881");
        assignmentMap.put(21,"DL51EV8477");
        assignmentMap.put(35,"DL51GD8487");
    }

}

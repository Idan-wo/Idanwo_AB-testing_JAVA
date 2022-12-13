package com.ayotycoon.services;

import com.ayotycoon.services.WSManager.WSManager;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;


public class CONSTANTS {
    public static ObjectMapper OM = new ObjectMapper();
    public static String userHeaderName;
    public static String userKey;
    public static String redisSubKey = "redis";
    public static Long userExpirationTime;
    public static WSManager wsManager;
}

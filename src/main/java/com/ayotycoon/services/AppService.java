package com.ayotycoon.services;


import com.ayotycoon.services.WSManager.WSManager;
import com.ayotycoon.utils.Util;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@Data
public class AppService {
    private  boolean production = false;
    private  String elasticSearchUrl;
    private  String elasticSearchHost;
    private  String id = Util.generateRandomString(17);
    private  Integer elasticSearchPort;

    @Autowired
    Environment env;


    @Value("${org.mode:false}")
    private  boolean orgMode;
    @Value("${org.header.id:org-id}")
    private String orgIdHeader;

    public String[] getActiveProfiles(){
        return env.getActiveProfiles();
    }
    public boolean isEnvTest(){
        return isEnvContain("test");
    }
    public boolean isEnvProd(){
        return isEnvContain("prod");
    }
    public boolean isEnvDev(){
        return isEnvContain("dev");
    }
    public boolean isEnvContain(String s){
        for(String env: getActiveProfiles()){
         if(env.equals(s)) return true;
        }
        return false;
    }


}



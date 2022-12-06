package com.ayotycoon.services;


import com.ayotycoon.utils.Util;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${org.mode:false}")
    private  boolean orgMode;
    @Value("${org.header.id:org-id}")
    private String orgIdHeader;


}



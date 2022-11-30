package com.ayotycoon.services;


import com.ayotycoon.utils.Util;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.utility.RandomString;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Slf4j
@Service
@Data
public class AppService {
    private  boolean production = false;
    private  String elasticSearchUrl;
    private  String elasticSearchHost;
    private  String id = Util.generateRandomString(17);
    private  Integer elasticSearchPort;
    private  ObjectMapper OM = new ObjectMapper();

}



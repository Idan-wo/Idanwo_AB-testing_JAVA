package com.ayotycoon.exceptions;

import org.springframework.http.HttpStatus;

public class OrgIdHeaderNotFoundException extends ExceptionWithHttpCode{

    public OrgIdHeaderNotFoundException() {
        super("Organisation id not in header" ,HttpStatus.BAD_REQUEST);
    }
}

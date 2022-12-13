package com.ayotycoon.exceptions;

import org.springframework.http.HttpStatus;

public class OrgIdHeaderNotFoundException extends ExceptionWithHttpCode{

    public OrgIdHeaderNotFoundException() {
        super("Organisation id not in header" ,HttpStatus.PRECONDITION_FAILED);
    }
    public OrgIdHeaderNotFoundException(String id) {
        super("Organisation id '%s' not in header".formatted(id) ,HttpStatus.PRECONDITION_FAILED);
    }
}

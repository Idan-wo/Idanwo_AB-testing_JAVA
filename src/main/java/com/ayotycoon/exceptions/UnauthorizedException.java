package com.ayotycoon.exceptions;

import org.springframework.http.HttpStatus;

public class UnauthorizedException extends ExceptionWithHttpCode{

    public UnauthorizedException() {
        super("Unauthorized" ,HttpStatus.UNAUTHORIZED);
    }
}

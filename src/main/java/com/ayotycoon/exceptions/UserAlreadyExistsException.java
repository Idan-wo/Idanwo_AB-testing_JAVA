package com.ayotycoon.exceptions;

import org.springframework.http.HttpStatus;

public class UserAlreadyExistsException extends ExceptionWithHttpCode{

    public UserAlreadyExistsException() {
        super("User already exists" ,HttpStatus.PRECONDITION_FAILED);
    }

}

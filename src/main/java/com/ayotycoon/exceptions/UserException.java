package com.ayotycoon.exceptions;

import org.springframework.http.HttpStatus;

public class UserException extends Exception{


    public static class AlreadyExists extends ExceptionWithHttpCode{

        public AlreadyExists() {
            super("User already exists" ,HttpStatus.PRECONDITION_FAILED);
        }

    }

}

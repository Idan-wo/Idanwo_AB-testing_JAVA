package com.ayotycoon.exceptions;

import org.springframework.http.HttpStatus;

public class ExceptionWithHttpCode extends Exception{
    private HttpStatus status;

    public ExceptionWithHttpCode(String s, HttpStatus status){
        super(s);
        this.status = status;
    }
    public HttpStatus getStatus(){
        return status;
    }
}

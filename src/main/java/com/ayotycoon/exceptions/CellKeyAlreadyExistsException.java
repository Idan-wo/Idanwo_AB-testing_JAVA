package com.ayotycoon.exceptions;

import org.springframework.http.HttpStatus;

public class CellKeyAlreadyExistsException extends ExceptionWithHttpCode{

    public CellKeyAlreadyExistsException() {
        super("Cell key already exists" ,HttpStatus.PRECONDITION_FAILED);
    }
    public CellKeyAlreadyExistsException(String key) {
        super("Cell key '%s' already exists".formatted(key) ,HttpStatus.PRECONDITION_FAILED);
    }

}

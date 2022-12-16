package com.ayotycoon.exceptions;

import org.springframework.http.HttpStatus;

public class CellException  extends Exception{
    public static class KeyAlreadyExists extends ExceptionWithHttpCode{

        public KeyAlreadyExists() {
            super("Cell key already exists" ,HttpStatus.PRECONDITION_FAILED);
        }
        public KeyAlreadyExists(String key) {
            super("Cell key '%s' already exists".formatted(key) ,HttpStatus.PRECONDITION_FAILED);
        }

    }
    public static class KeyDoesNotExists extends ExceptionWithHttpCode{

        public KeyDoesNotExists() {
            super("Cell key doesnt exists" ,HttpStatus.PRECONDITION_FAILED);
        }
        public KeyDoesNotExists(String key) {
            super("Cell key '%s' doesnt exists".formatted(key) ,HttpStatus.PRECONDITION_FAILED);
        }

    }
    public static class CellTypesDoNotMatch extends ExceptionWithHttpCode{

        public CellTypesDoNotMatch() {
            super("Cell types do not match" ,HttpStatus.PRECONDITION_FAILED);
        }


    }
    public static class IncorrectCell extends ExceptionWithHttpCode{

        public IncorrectCell() {
            super("Incorrect Cell parameter" ,HttpStatus.PRECONDITION_FAILED);
        }
        public IncorrectCell(String msg) {
            super(msg ,HttpStatus.PRECONDITION_FAILED);
        }
    }
    public static class InvalidCellKey extends ExceptionWithHttpCode{

        public InvalidCellKey() {
            super("Incorrect Cell key" ,HttpStatus.PRECONDITION_FAILED);
        }
        public InvalidCellKey(String msg) {
            super(msg ,HttpStatus.PRECONDITION_FAILED);
        }
    }

}

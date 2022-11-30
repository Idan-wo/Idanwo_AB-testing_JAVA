package com.ayotycoon;




import com.ayotycoon.exceptions.ExceptionWithHttpCode;
import com.ayotycoon.daos.responses.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorHandler {
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Object> exception(Exception exception) {
        return new ResponseEntity(new ErrorResponse(exception), HttpStatus.valueOf(400));
    }
    @ExceptionHandler(value = ExceptionWithHttpCode.class)
    public ResponseEntity exceptionWithCode(ExceptionWithHttpCode exception) {
        return new ResponseEntity(new ErrorResponse(exception), exception.getStatus());
    }
}

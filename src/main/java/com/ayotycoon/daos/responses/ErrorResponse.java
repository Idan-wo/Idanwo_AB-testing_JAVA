package com.ayotycoon.daos.responses;

import lombok.Data;

@Data
public class ErrorResponse {
    Object error;
    String message;
    Boolean success = false;


    public ErrorResponse(String error) {
        this.error = error;

        var s = error.split("-");

        this.message = s.length > 1 ? s[1] : error;


    }

    public ErrorResponse(Exception e) {


        var error = e.getMessage();

        this.error = error;

        var s = error.split("-");

        this.message = s.length > 1 ? s[1] : error;

        e.printStackTrace();


    }

}

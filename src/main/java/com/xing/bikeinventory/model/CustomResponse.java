package com.xing.bikeinventory.model;

import org.springframework.http.HttpStatus;

public class CustomResponse implements RespType {
    public final HttpStatus httpStatus;
    public final int statusCode;
    public final String message;

     public CustomResponse(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.statusCode = httpStatus.value();
        this.message = message;
    }
}

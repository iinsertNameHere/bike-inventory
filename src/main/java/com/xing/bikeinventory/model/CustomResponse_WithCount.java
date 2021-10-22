package com.xing.bikeinventory.model;

import org.springframework.http.HttpStatus;

public class CustomResponse_WithCount extends CustomResponse {
    public final int count;
    public CustomResponse_WithCount(HttpStatus httpStatus, String message, int count) {
        super(httpStatus, message);
        this.count = count;
    }
}

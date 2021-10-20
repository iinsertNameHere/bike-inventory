package com.xing.bikeinventory.model;

import org.springframework.http.HttpStatus;

public class CustomResponse_WithObject<T> extends CustomResponse {

    public final T obj;

    public CustomResponse_WithObject(HttpStatus httpStatus, String message, T obj) {
        super(httpStatus, message);
        this.obj = obj;
    }
}

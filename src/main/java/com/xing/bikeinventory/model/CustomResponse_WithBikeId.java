package com.xing.bikeinventory.model;

import org.springframework.http.HttpStatus;

public class CustomResponse_WithBikeId extends CustomResponse {
    public String bikeId;
    public CustomResponse_WithBikeId(HttpStatus httpStatus, String message, String bikeId) {
        super(httpStatus, message);
        this.bikeId = bikeId;
    }
}

package com.xing.bikeinventory.model;

import com.xing.bikeinventory.controller.BikeState;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.security.core.userdetails.User;

import java.time.Instant;

public class Bike implements RespType {
    
    @Id
    public Long id;

    @CreatedDate
    public Instant createdDate;

    @CreatedBy
    public String createdBy;

    @LastModifiedBy
    public String lastModifiedBy;

    public String state = BikeState.waiting.toString();

    public String brand;
    public String color;
    public int numberOfGears;
    
    public Bike() {}
    
    public Bike(String brand, String color, int numberOfGears, String state) {
        this.brand = brand;
        this.color = color;
        this.numberOfGears = numberOfGears;
        this.state = state;
    }

    @Override
    public String toString() {
        return String.format(
                "Bike{id='%s', brand='%s', color='%s', numberOfGears=%s, createdDate='%s', createdBy='%s', lastModifiedBy='%s', state='%s'}",
                id, brand, color, numberOfGears, createdDate, createdBy, lastModifiedBy, state
        );
    }

    public JBike toJBike() {
        var jBike = new JBike();
        jBike.setBrand(this.brand);
        jBike.setColor(BikeColor.valueOf(this.color));
        jBike.setNumberOfGears(this.numberOfGears);
        jBike.setState(BikeState.valueOf(this.state));
        return jBike;
    }
}

package com.xing.bikeinventory.model;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.security.core.userdetails.User;

import java.time.Instant;

public class Bike implements RespType {
    
    @Id
    public String id;

    @CreatedDate
    public Instant createdDate;

    @CreatedBy
    public String createdBy;

    @LastModifiedBy
    public String lastModifiedBy;

    public String brand;
    public String color;
    public int numberOfGears;
    
    public Bike() {}
    
    public Bike(String brand, String color, int numberOfGears) {
        this.brand = brand;
        this.color = color;
        this.numberOfGears = numberOfGears;
    }

    @Override
    public String toString() {
        return String.format(
                "Bike{id='%s', brand='%s', color='%s', numberOfGears=%s, createdDate='%s', createdBy='%s', lastModifiedBy='%s'}",
                id, brand, color, numberOfGears, createdDate, createdBy, lastModifiedBy
        );
    }

    public JBike toJBike() {
        var jBike = new JBike();
        jBike.setBrand(this.brand);
        jBike.setColor(BikeColor.valueOf(this.color));
        jBike.setNumberOfGears(this.numberOfGears);
        return jBike;
    }
}

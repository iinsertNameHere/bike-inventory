package com.xing.bikeinventory.model;

import org.springframework.data.annotation.Id;

public class Bike implements RespType {
    
    @Id
    public String id;

    public String brand;
    public String color;
    public int numberOfGears;
    
    public Bike() {}
    
    public Bike(String id, String brand, String color, int numberOfGears) {
        this.id = id;
        this.brand = brand;
        this.color = color;
        this.numberOfGears = numberOfGears;
    }

    @Override
    public String toString() {
        return String.format(
                "Bike{id=%s, brand='%s', color='%s', numberOfGears=%s}",
                id, brand, color, numberOfGears
        );
    }
}

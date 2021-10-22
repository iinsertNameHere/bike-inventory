package com.xing.bikeinventory.model;

public class JBike implements RespType {

    public String brand;
    public String color;
    public int numberOfGears;

    public JBike() {}

    public JBike(String brand, String color, int numberOfGears) {
        this.brand = brand;
        this.color = color;
        this.numberOfGears = numberOfGears;
    }

    @Override
    public String toString() {
        return String.format(
                "Bike{brand='%s', color='%s', numberOfGears=%s}",
                brand, color, numberOfGears
        );
    }
}

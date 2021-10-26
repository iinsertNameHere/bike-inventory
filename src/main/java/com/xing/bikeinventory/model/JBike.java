package com.xing.bikeinventory.model;

public class JBike implements RespType {

    public String brand = "None";
    public BikeColor color = BikeColor.None;
    public int numberOfGears = 0;

    @Override
    public String toString() {
        return String.format(
                "Bike{brand='%s', color='%s', numberOfGears=%s}",
                getBrand(), getColor(), getNumberOfGears()
        );
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public BikeColor getColor() {
        return color;
    }

    public void setColor(BikeColor color) {
        this.color = color;
    }

    public int getNumberOfGears() {
        return numberOfGears;
    }

    public void setNumberOfGears(int numberOfGears) {
        this.numberOfGears = numberOfGears;
    }

    public boolean hasError() {
        return (getBrand().equals("None") || color == BikeColor.None || numberOfGears <= 0);
    }
}

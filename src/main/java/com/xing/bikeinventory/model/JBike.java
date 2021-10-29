package com.xing.bikeinventory.model;

import com.xing.bikeinventory.controller.BikeState;

public class JBike implements RespType {

    public String brand = "None";
    public BikeColor color = BikeColor.None;
    public int numberOfGears = 0;
    public BikeState state = BikeState.waiting;

    @Override
    public String toString() {
        return String.format(
                "Bike{brand='%s', color='%s', numberOfGears=%s, state='%s'}",
                getBrand(), getColor(), getNumberOfGears(), getState()
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

    public BikeState getState() {return state;}

    public void setState(BikeState state) {this.state = state;}

    public boolean hasError() {
        return (getBrand().equals("None") || color == BikeColor.None || numberOfGears <= 0);
    }
}

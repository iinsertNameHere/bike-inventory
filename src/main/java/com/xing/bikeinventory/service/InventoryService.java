package com.xing.bikeinventory.service;

import com.xing.bikeinventory.model.Bike;
import com.xing.bikeinventory.model.JBike;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class InventoryService {

    private final BikeRepository inventory;
    public final String host = "http://localhost:9000";

    public InventoryService(BikeRepository inventory1) {
        this.inventory = inventory1;
    }

    public String addBike(JBike newBike) {
        Bike bike = new Bike(null, newBike.getBrand(), newBike.getColor().toString(), newBike.getNumberOfGears());

        inventory.save(bike);

        System.out.println("Added new bike to inventory: " + bike);
        return bike.id;
    }

    public Collection<Bike> getAllBikes() {
        return inventory.findAll();
    }

    public void removeBike(String bikeId) {
        inventory.deleteById(bikeId);
        System.out.printf("Removed bike '%s' from inventory.%n", bikeId);
    }

    public void removeAllBikes() {
        for (Bike bike : inventory.findAll()) {
            inventory.deleteById(bike.id);
            System.out.printf("Removed bike '%s' from inventory.%n", bike.id);
        }
    }

    public boolean containsBike(String id) {
        return inventory.existsById(id);
    }

    public Optional<Bike> getBikeById(String id) {
        return inventory.findById(id);
    }

    public List<Bike> getBikesByColor(String color) {
        return inventory.findAllByColorIgnoreCase(color);
    }

    public int countBikesByColor(String color) {
        return (int) inventory.countByColorIgnoreCase(color);
    }

    public List<Bike> getBikesByBrand(String brand) {
        return inventory.findAllByBrandIgnoreCase(brand);
    }

    public int countBikesByBrand(String brand) {
        return (int) inventory.countByBrandIgnoreCase(brand);
    }

    public List<Bike> getBikesByNumOfGears(int numOfGears) {
        return inventory.findAllByNumberOfGears(numOfGears);
    }

    public int countBikesByNumOfGears(int numOfGears) {
        return (int)inventory.countByNumberOfGears(numOfGears);
    }

}
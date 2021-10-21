package com.xing.bikeinventory.service;

import com.xing.bikeinventory.model.Bike;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class InventoryService {

    private final AtomicInteger idCounter = new AtomicInteger();
    private final BikeRepository inventory;

    public InventoryService(BikeRepository inventory1) {
        this.inventory = inventory1;
    }

    public String addBike(String brand, String color, int numOfGears) {
        Bike bike = new Bike(null, brand, color, numOfGears);

        inventory.save(bike);

        System.out.println("Added new bike to inventory: " + bike);
        return bike.id;
    }

    public Collection<Bike> getAllBikes() {
        return inventory.findAll();
    }

    public void removeBike(String bikeId) {
        inventory.deleteById(bikeId);
        System.out.printf("Removed bike %s from inventory.%n", bikeId);
    }

    public Optional<Bike> getBikeById(String id) {
        return inventory.findById(id);
    }

    public boolean containsBike(String id) {
        return inventory.existsById(id);
    }
}

package com.xing.bikeinventory.service;

import com.xing.bikeinventory.model.Bike;
import com.xing.bikeinventory.model.JBike;
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

    public String addBike(JBike newBike) {
        Bike bike = new Bike(null, newBike.brand, newBike.color, newBike.numberOfGears);

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

    public Optional<Bike> getBikeById(String id) {
        return inventory.findById(id);
    }

    public boolean containsBike(String id) {
        return inventory.existsById(id);
    }
}

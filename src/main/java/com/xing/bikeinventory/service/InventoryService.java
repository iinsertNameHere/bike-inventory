package com.xing.bikeinventory.service;

import com.xing.bikeinventory.model.Bike;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class InventoryService {

    private final AtomicInteger idCounter = new AtomicInteger();
    private final Map<Integer, Bike> inventory = new ConcurrentHashMap<>();

    public int addBike(String brand, String color, int numOfGears) {
        int id = idCounter.incrementAndGet();
        Bike bike = Bike.builder()
                .id(id)
                .brand(brand)
                .color(color)
                .numberOfGears(numOfGears)
                .build();

        inventory.put(id, bike);

        System.out.println("Added new bike to inventory: " + bike);
        return bike.getId();
    }

    public Collection<Bike> getAllBikes() {
        return inventory.values();
    }

    public void removeBike(int bikeId) {
        Bike bike = inventory.get(bikeId);
        inventory.remove(bikeId);
        System.out.println(String.format("Removed bike %d from inventory.", bikeId));
    }

    public Bike getBikeById(int id) {
        return inventory.get(id);
    }

    public boolean containsBike(int id) {
        return inventory.containsKey(id);
    }

    public int getCurrentBikeCount() {
        return idCounter.get();
    }
}

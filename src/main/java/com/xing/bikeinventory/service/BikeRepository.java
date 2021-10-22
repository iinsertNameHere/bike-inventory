package com.xing.bikeinventory.service;

import java.util.List;

import com.xing.bikeinventory.model.Bike;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BikeRepository extends MongoRepository<Bike, String> {

    List<Bike> findAllByColorIgnoreCase(String color);
    long countByColorIgnoreCase(String color);
    List<Bike> findAllByBrandIgnoreCase(String brand);
    long countByBrandIgnoreCase(String brand);
    List<Bike> findAllByNumberOfGears(int numberOfGears);
    long  countByNumberOfGears(int numberOfGears);

}

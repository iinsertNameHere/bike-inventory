package com.xing.bikeinventory.service;

import java.util.List;

import com.xing.bikeinventory.model.Bike;
import org.springframework.data.repository.CrudRepository;

public interface BikeRepository extends CrudRepository<Bike, Long> {

    List<Bike> findAll();
    List<Bike> findAllByColorIgnoreCase(String color);
    long countByColorIgnoreCase(String color);
    List<Bike> findAllByBrandIgnoreCase(String brand);
    long countByBrandIgnoreCase(String brand);
    List<Bike> findAllByNumberOfGears(int numberOfGears);
    long countByNumberOfGears(int numberOfGears);
    List<Bike> findAllByCreatedBy(String name);
}

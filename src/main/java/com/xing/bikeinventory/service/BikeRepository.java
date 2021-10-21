package com.xing.bikeinventory.service;

import java.util.List;

import com.xing.bikeinventory.model.Bike;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BikeRepository extends MongoRepository<Bike, String> {

}

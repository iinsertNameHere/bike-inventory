package com.xing.bikeinventory.controller;

import com.xing.bikeinventory.model.Bike;
import com.xing.bikeinventory.model.CustomResponse;
import com.xing.bikeinventory.model.CustomResponse_WithBikeId;
import com.xing.bikeinventory.service.InventoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class InventoryController {

    private final InventoryService service;

    public InventoryController(InventoryService service) {
        this.service = service;
    }


    // This exposes a POST endpoint to add a new bike
    // You can call this endpoint e.g. from your console with this command:
    //      curl -XPOST "http://localhost:8080/bike/new?brand=XYZ+Bikes&color=Black&numOfGears=13"
    @PostMapping("/bike/new")
    public ResponseEntity addBike(@RequestParam String brand, @RequestParam String color, @RequestParam int numOfGears) {
        System.out.println("About to add a new bike: {brand=" + brand + ", color=" + color + ", numberOfGears=" + numOfGears + "}");
        var resp = new CustomResponse_WithBikeId(HttpStatus.OK, "Added new bike to inventory.", service.addBike(brand, color, numOfGears));
        return new ResponseEntity(resp, resp.httpStatus);
    }

    @GetMapping("/bike/all")
    public Collection<Bike> showAllBikes() {
        return service.getAllBikes();
    }

    @PostMapping("/bike/remove")
    public ResponseEntity removeBike(@RequestParam int id) {
        var error = new CustomResponse(HttpStatus.BAD_REQUEST, String.format("No bike with id '%d' in inventory.", id));
        var resp = new  CustomResponse_WithBikeId(HttpStatus.OK, "Removed bike from inventory.", id);
        if (!service.containsBike(id))
            return new ResponseEntity(error, error.httpStatus);
        return new ResponseEntity(resp, resp.httpStatus);
    }

    @GetMapping("/bike/get")
    public Object getBikeById(@RequestParam int id) {
        var error = new CustomResponse(HttpStatus.BAD_REQUEST, String.format("No bike with id '%d' in inventory.", id));
        if (!service.containsBike(id))
            return new ResponseEntity(error, error.httpStatus);
        return service.getBikeById(id);

    }
}

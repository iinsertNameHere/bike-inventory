package com.xing.bikeinventory.controller;

import com.xing.bikeinventory.model.*;
import com.xing.bikeinventory.service.InventoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.annotation.HttpMethodConstraint;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
public class InventoryController {

    private final InventoryService service;

    public InventoryController(InventoryService service) {
        this.service = service;
    }


    // This exposes a POST endpoint to add a new bike
    // You can call this endpoint e.g. from your console with this command:
    //      curl -XPOST "http://localhost:8080/bike/new?brand=XYZ+Bikes&color=Black&numOfGears=13"
    @RequestMapping(value = "/api/bike/{brand}/{color}/{numOfGears}", method = RequestMethod.POST)
    public ResponseEntity<CustomResponse> addBike(@PathVariable String brand, @PathVariable String color, @PathVariable int numOfGears) {
        System.out.println("About to add a new bike: {brand=" + brand + ", color=" + color + ", numberOfGears=" + numOfGears + "}");
        var resp = new CustomResponse_WithBikeId(HttpStatus.OK, "Added new bike to inventory.", service.addBike(brand, color, numOfGears));
        return new ResponseEntity<CustomResponse>(resp, resp.httpStatus);
    }

    @RequestMapping(value = "/api/bike/{id}", method = RequestMethod.GET)
    public ResponseEntity<RespType> getBikeById(@PathVariable(required = false) String id) {
        var error = new CustomResponse(HttpStatus.NOT_FOUND, String.format("No bike with id '%d' in inventory.", id));
        var bike = service.getBikeById(id);
        if (bike.isEmpty())
            return new ResponseEntity<>(error, error.httpStatus);
        return new ResponseEntity<>(bike.get(), HttpStatus.OK);
    }

    @RequestMapping(value = "/api/bike", method = RequestMethod.GET)
    public Collection<Bike> showAllBikes() {
        return service.getAllBikes();
    }

    @RequestMapping(value = "/api/bike/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<CustomResponse> removeBike(@PathVariable String id) {
        var error = new CustomResponse(HttpStatus.BAD_REQUEST, String.format("No bike with id '%d' in inventory.", id));
        var resp = new  CustomResponse_WithBikeId(HttpStatus.OK, "Removed bike from inventory.", id);
        if (!service.containsBike(id))
            return new ResponseEntity<CustomResponse>(error, error.httpStatus);
        service.removeBike(id);
        return new ResponseEntity<CustomResponse>(resp, resp.httpStatus);
    }
}

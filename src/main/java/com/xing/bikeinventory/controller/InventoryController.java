package com.xing.bikeinventory.controller;

import com.xing.bikeinventory.model.*;
import com.xing.bikeinventory.service.InventoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Random;

@RestController
public class InventoryController {

    private final InventoryService service;
    private final String pwd;

    private String getSaltString(int len) {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890-_";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < len) {
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;
    }

    public InventoryController(InventoryService service) {
        this.service = service;
        this.pwd = getSaltString(18);
        System.out.printf("API Key for this Session: '%s'\n", pwd);
    }

    @RequestMapping(value = "/api/bike", method = RequestMethod.POST)
    public ResponseEntity<CustomResponse> addBike(@RequestBody JBike newBike) {
        var resp = new CustomResponse_WithBikeId(HttpStatus.OK, "Added new bike to inventory.", service.addBike(newBike));
        return new ResponseEntity<CustomResponse>(resp, resp.httpStatus);
    }

    @RequestMapping(value = "/api/bike/{id}", method = RequestMethod.GET)
    public ResponseEntity<RespType> getBikeById(@PathVariable(required = false) String id) {
        var error = new CustomResponse(HttpStatus.NOT_FOUND, String.format("No bike with id '%s' in inventory.", id));
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
        var error = new CustomResponse(HttpStatus.BAD_REQUEST, String.format("No bike with id '%s' in inventory.", id));
        var resp = new  CustomResponse_WithBikeId(HttpStatus.OK, "Removed bike from inventory.", id);
        if (!service.containsBike(id))
            return new ResponseEntity<CustomResponse>(error, error.httpStatus);
        service.removeBike(id);
        return new ResponseEntity<CustomResponse>(resp, resp.httpStatus);
    }

    @RequestMapping(value = "/api/bike", method = RequestMethod.DELETE)
    public ResponseEntity<CustomResponse> removeAllBikes(@RequestParam(value = "key") String sessionPwd) {
        if (sessionPwd.equals(pwd)) {
            if (service.getAllBikes().size() > 0) {
                service.removeAllBikes();
                var resp = new CustomResponse(HttpStatus.OK, "Removed all Bikes. ");
                return new ResponseEntity<>(resp, resp.httpStatus);
            }
            else
                return new ResponseEntity<>(new CustomResponse(HttpStatus.OK, "No Bikes found."), HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(new CustomResponse(HttpStatus.UNAUTHORIZED, "Key incorrect!"), HttpStatus.UNAUTHORIZED);
    }

    @RequestMapping(value = "/api/color/{color}", method = RequestMethod.GET)
    public Collection<Bike> getBikesByColor(@PathVariable String color) {
        return service.getBikesByColor(color);
    }

    @RequestMapping(value = "/api/color/{color}/count", method = RequestMethod.GET)
    public ResponseEntity<RespType> getCountOfBikesWithColor(@PathVariable String color) {
        var resp = new CustomResponse_WithCount(HttpStatus.OK, String.format("Count of bikes with color: '%s'", color), service.countBikesByColor(color));
        return new ResponseEntity<>(resp, resp.httpStatus);
    }

    @RequestMapping(value = "/api/brand/{brand}", method = RequestMethod.GET)
    public Collection<Bike> getBikesByBrand(@PathVariable String brand) {
        return service.getBikesByBrand(brand);
    }

    @RequestMapping(value = "/api/brand/{brand}/count", method = RequestMethod.GET)
    public ResponseEntity<RespType> getCountOfBikesFromBrand(@PathVariable String brand) {
        var resp = new CustomResponse_WithCount(HttpStatus.OK, String.format("Count of bikes from brand: '%s'", brand), service.countBikesByBrand(brand));
        return new ResponseEntity<>(resp, resp.httpStatus);
    }

    @RequestMapping(value = "/api/numOfGears/{gears}", method = RequestMethod.GET)
    public Collection<Bike> getBikesByGears(@PathVariable int gears) {
        return service.getBikesByNumOfGears(gears);
    }

    @RequestMapping(value = "/api/numOfGears/{gears}/count", method = RequestMethod.GET)
    public ResponseEntity<RespType> getCountOfBikesNumOfGears(@PathVariable int gears) {
        var resp = new CustomResponse_WithCount(HttpStatus.OK, String.format("Count of bikes from brand: '%s'", gears), service.countBikesByNumOfGears(gears));
        return new ResponseEntity<>(resp, resp.httpStatus);
    }
}

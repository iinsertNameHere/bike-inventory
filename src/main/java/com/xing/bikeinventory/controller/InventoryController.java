package com.xing.bikeinventory.controller;

import com.xing.bikeinventory.model.*;
import com.xing.bikeinventory.service.InventoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.annotation.HttpMethodConstraint;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
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
                var resp = new CustomResponse(HttpStatus.OK, "Removed all Bikes.");
                return new ResponseEntity<>(resp, resp.httpStatus);
            }
            else
                return new ResponseEntity<>(new CustomResponse(HttpStatus.OK, "No Bikes found."), HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(new CustomResponse(HttpStatus.UNAUTHORIZED, "Key incorrect!"), HttpStatus.UNAUTHORIZED);
    }
}

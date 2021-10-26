package com.xing.bikeinventory.controller;

import com.xing.bikeinventory.model.*;
import com.xing.bikeinventory.service.InventoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
public class WebController {

    private final InventoryService service;

    public WebController(InventoryService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String index(Model model) {
        Map<String, Object> attr = new HashMap<>();
        attr.put("allBikes", service.getAllBikes());
        model.addAllAttributes(attr);
        return "index";
    }

    @GetMapping("/{id}")
    public String removeBike(@PathVariable String id) {
        service.removeBike(id);
        return "redirect:/";
    }

    @GetMapping("/new")
    public String newBikeForm(Model model) {
        model.addAttribute("allColors", BikeColor.values());
        model.addAttribute("newBike", new JBike());
        model.addAttribute("error", "");
        return "new-bike";
    }

    @PostMapping("/new")
    public String newBikeSubmit(@ModelAttribute JBike jBike, Model model) {
        if (jBike.hasError()) {
            model.addAttribute("allColors", BikeColor.values());
            model.addAttribute("newBike", new JBike());
            model.addAttribute("error", "Failed to add your bike!");
            return "new-bike";
        }
        service.addBike(jBike);
        return "redirect:/";
    }
}

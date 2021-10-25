package com.xing.bikeinventory.controller;

import com.xing.bikeinventory.model.Bike;
import com.xing.bikeinventory.service.InventoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
        attr.put("service", service);
        attr.put("host", service.host);
        model.addAllAttributes(attr);
        return "index";
    }

}

package com.xing.bikeinventory.controller;

import com.xing.bikeinventory.model.BikeColor;
import com.xing.bikeinventory.model.JBike;
import com.xing.bikeinventory.service.InventoryService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;


@Controller
public class WebController {

    private final InventoryService service;

    public WebController(InventoryService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("allBikes", service.getAllBikes());
        return "index";
    }

    @GetMapping("/new")
    public String newBikeForm(Model model) {
        model.addAttribute("allColors", BikeColor.values());
        model.addAttribute("newBike", new JBike());
        model.addAttribute("error", "");
        return "new-bike";
    }

    @PostMapping("/new")
    public ModelAndView newBikeSubmit(@ModelAttribute JBike jBike, RedirectAttributes redirectAttributes) {
        if (jBike.hasError()) {
            ModelAndView model = new ModelAndView("new-bike");
            model.addObject("allColors", BikeColor.values());
            model.addObject("newBike", new JBike());
            model.addObject("error", "Failed to add your bike!");
            model.setStatus(HttpStatus.BAD_REQUEST);
            return model;
        }
        redirectAttributes.addFlashAttribute("msg", String.format("The id of your Bike is: %s", service.addBike(jBike)));
        return new ModelAndView(new RedirectView("/"));
    }
}

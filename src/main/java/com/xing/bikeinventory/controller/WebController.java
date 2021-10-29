package com.xing.bikeinventory.controller;

import com.xing.bikeinventory.model.Bike;
import com.xing.bikeinventory.model.BikeColor;
import com.xing.bikeinventory.model.JBike;
import com.xing.bikeinventory.service.InventoryService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.Optional;


@Controller
public class WebController {

    private final InventoryService service;

    public WebController(InventoryService service) {
        this.service = service;
    }

    private String admin = "service.admin";

    @GetMapping("/")
    public String index(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getName().equals(admin)) {
            model.addAttribute("allBikes", service.getAllBikes());
            return "index";
        }
        model.addAttribute("allBikes", service.getBikesByOwner(auth.getName()));
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/new")
    public String newBikeForm(Model model) {
        model.addAttribute("allColors", BikeColor.values());
        model.addAttribute("newBike", new JBike());
        model.addAttribute("error", "");
        return "new-bike";
    }

    @GetMapping("/editor/{id}")
    public ModelAndView editBike(@PathVariable String id, RedirectAttributes redirectAttributes) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!service.containsBike(id)) {
            return new ModelAndView(new RedirectView("/"));
        }
        ModelAndView model = new ModelAndView("editor");
        model.addObject("allColors", BikeColor.values());
        model.addObject("newBike", service.getBikeById(id).get().toJBike());
        if (auth.getName().equals(admin)) {
            model.addObject("isAdmin", true);
            model.addObject("allStates", BikeState.values());
        }
        model.setStatus(HttpStatus.OK);
        return model;
    }

    @PostMapping("/editor/{id}")
    public ModelAndView editBike(@PathVariable String id, @ModelAttribute JBike jBike, RedirectAttributes redirectAttributes) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!service.containsBike(id)) {
            return new ModelAndView(new RedirectView("/"));
        }
        if (jBike.hasError()) {
            ModelAndView model = new ModelAndView("editor");
            model.addObject("allColors", BikeColor.values());
            model.addObject("newBike", service.getBikeById(id).get().toJBike());
            if (auth.getName().equals(admin)) {
                model.addObject("isAdmin", true);
                model.addObject("allStates", BikeState.values());
            }
            model.addObject("error", "Failed to update your bike!");
            model.setStatus(HttpStatus.BAD_REQUEST);
            return model;
        }
        redirectAttributes.addFlashAttribute("msg", String.format("Updated Bike: %s", service.updateBike(id, jBike)));
        return new ModelAndView(new RedirectView("/"));
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

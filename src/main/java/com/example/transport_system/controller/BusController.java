package com.example.transport_system.controller;

import com.example.transport_system.model.Bus;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class BusController {

    @PostMapping("/addBus")
    public String addBus(
            @Valid @ModelAttribute Bus bus,
            BindingResult result) {

        if (result.hasErrors()) {
            return "addBus";
        }

        return "success";
    }
}
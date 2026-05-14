package com.example.transport_system.controller;

import com.example.transport_system.model.Booking;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class BookingController {

    @PostMapping("/booking")
    public String bookSeat(
            @Valid @ModelAttribute Booking booking,
            BindingResult result) {

        if (result.hasErrors()) {
            return "booking";
        }

        return "success";
    }
}
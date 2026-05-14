package com.example.transport_system.controller;

import com.example.transport_system.model.User;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    @PostMapping("/register")
    public String registerUser(
            @Valid @ModelAttribute User user,
            BindingResult result) {

        if (result.hasErrors()) {
            return "register";
        }

        return "success";
    }
}
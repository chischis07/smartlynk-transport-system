package com.example.transport_system.controller;

import com.example.transport_system.entity.Role;
import com.example.transport_system.entity.User;
import com.example.transport_system.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("/")
    public String home() {
        return "LandingPage";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }


    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute("user") User user,
                               BindingResult result,
                               Model model) {
        user.setRole(Role.USER);

        if (result.hasErrors()) {
            return "register";
        }

        try {
            userService.registerUser(user);
            return "redirect:/login?registered=true";
        } catch (RuntimeException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "register";
        }
    }


    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }


    @PostMapping("/login")
    public String loginUser(@RequestParam String email,
                            @RequestParam String password,
                            Model model) {
        Optional<User> userOpt = userService.loginUser(email, password);

        if (userOpt.isPresent()) {
            User user = userOpt.get();
            if (user.getRole() == Role.ADMIN) {
                return "redirect:/admin/dashboard";
            }
            return "redirect:/user/dashboard";
        } else {
            model.addAttribute("errorMessage", "Invalid email or password");
            return "login";
        }
    }

    // Showing user dashboard
    @GetMapping("/user/dashboard")
    public String userDashboard(Model model) {
        return "user-dashboard";
    }
}
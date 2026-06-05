package com.example.transport_system.controller;

import com.example.transport_system.entity.Admin;
import com.example.transport_system.entity.User;
import com.example.transport_system.service.AdminService;
import com.example.transport_system.service.BookingService;
import com.example.transport_system.service.BusService;
import com.example.transport_system.service.RouteService;
import com.example.transport_system.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
//import jakarta.servlet.http.HttpSession;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private UserService userService;

    @Autowired
    private BookingService bookingService;

    @Autowired
    private BusService busService;

    @Autowired
    private RouteService routeService;


    @GetMapping("/login")
    public String showAdminLoginForm() {
        return "admin/login";
    }

    @PostMapping("/login")
    public String adminLogin(@RequestParam String username,
                             @RequestParam String password,
                             Model model) {
        boolean success = adminService.loginAdmin(username, password);
        if (success) {
            return "redirect:/admin/dashboard";
        } else {
            model.addAttribute("errorMessage", "Invalid username or password");
            return "admin/login";
        }
    }


    @GetMapping("/dashboard")
    public String adminDashboard(Model model) {
        // Pass stats to the dashboard template
        model.addAttribute("totalBookings", bookingService.getTotalBookings());
        model.addAttribute("totalBuses", busService.getAllBuses().size());
        model.addAttribute("totalRoutes", routeService.getAllRoutes().size());
        model.addAttribute("totalUsers", userService.getAllUsers().size());
        model.addAttribute("recentBookings", bookingService.getRecentBookings());
        model.addAttribute("mostBookedRoutes", bookingService.getMostBookedRoutes());
        return "admin/dashboard";
    }


    @GetMapping("/users")
    public String adminUserList(@RequestParam(defaultValue = "0") int page, Model model) {
        Page<User> userPage = userService.getAllUsersPaged(page, 10);
        model.addAttribute("userPage", userPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", userPage.getTotalPages());
        return "admin/users";
    }


    @GetMapping("/users/{id}")
    public String viewUser(@PathVariable Long id, Model model) {
        User user = userService.getUserById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        model.addAttribute("user", user);
        model.addAttribute("bookings", bookingService.getBookingsByUserId(id));
        return "admin/user-detail";
    }


    @GetMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "redirect:/admin/users";
    }
}
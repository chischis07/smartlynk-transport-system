package com.example.transport_system.controller;

import com.example.transport_system.entity.Route;
import com.example.transport_system.service.RouteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@Controller
public class RouteController {

    @Autowired
    private RouteService routeService;


    @GetMapping("/admin/routes")
    public String adminRouteList(@RequestParam(defaultValue = "0") int page, Model model) {
        Page<Route> routePage = routeService.getAllRoutesPaged(page, 10);
        model.addAttribute("routePage", routePage);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", routePage.getTotalPages());
        return "admin/routes"; // renders templates/admin/routes.html
    }


    @GetMapping("/admin/routes/add")
    public String showAddRouteForm(Model model) {
        model.addAttribute("route", new Route());
        return "admin/add-route"; // renders templates/admin/add-route.html
    }


    @PostMapping("/admin/routes/add")
    public String addRoute(@Valid @ModelAttribute("route") Route route,
                           BindingResult result) {
        if (result.hasErrors()) {
            return "admin/add-route";
        }
        routeService.saveRoute(route);
        return "redirect:/admin/routes";
    }


    @GetMapping("/admin/routes/edit/{id}")
    public String showEditRouteForm(@PathVariable Long id, Model model) {
        Route route = routeService.getRouteById(id)
                .orElseThrow(() -> new RuntimeException("Route not found"));
        model.addAttribute("route", route);
        return "admin/edit-route"; // renders templates/admin/edit-route.html
    }


    @PostMapping("/admin/routes/edit/{id}")
    public String editRoute(@PathVariable Long id,
                            @Valid @ModelAttribute("route") Route route,
                            BindingResult result) {
        if (result.hasErrors()) {
            return "admin/edit-route";
        }
        route.setRouteId(id);
        routeService.updateRoute(route);
        return "redirect:/admin/routes";
    }

    @GetMapping("/admin/routes/delete/{id}")
    public String deleteRoute(@PathVariable Long id) {
        routeService.deleteRoute(id);
        return "redirect:/admin/routes";
    }
}
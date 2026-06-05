package com.example.transport_system.controller;

import com.example.transport_system.entity.Bus;
import com.example.transport_system.service.BusService;
import com.example.transport_system.service.RouteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@Controller
public class BusController {

    @Autowired
    private BusService busService;

    @Autowired
    private RouteService routeService;


    @GetMapping("/buses")
    public String showAvailableBuses(@RequestParam(defaultValue = "0") int page,
                                     @RequestParam(defaultValue = "") String destination,
                                     Model model) {
        Page<Bus> busPage;

        if (!destination.isEmpty()) {
            // If user searched by destination, show filtered results (no pagination on search)
            model.addAttribute("buses", busService.searchByDestination(destination));
            model.addAttribute("searchDestination", destination);
            return "buses"; // simple list, no pagination
        } else {
            // Show all buses with pagination (5 per page)
            busPage = busService.getAllBusesPaged(page, 5);
            model.addAttribute("busPage", busPage);
            model.addAttribute("currentPage", page);
            model.addAttribute("totalPages", busPage.getTotalPages());
        }
        return "buses"; // renders templates/book-ride.html
    }

    @GetMapping("/admin/buses")
    public String adminBusList(@RequestParam(defaultValue = "0") int page, Model model) {
        Page<Bus> busPage = busService.getAllBusesPaged(page, 10);
        model.addAttribute("busPage", busPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", busPage.getTotalPages());
        return "admin/buses"; // renders templates/admin/book-ride.html
    }

    @GetMapping("/admin/buses/add")
    public String showAddBusForm(Model model) {
        model.addAttribute("bus", new Bus());
        model.addAttribute("routes", routeService.getAllRoutes()); // for the route dropdown
        return "admin/add-bus"; // renders templates/admin/add-bus.html
    }


    @PostMapping("/admin/buses/add")
    public String addBus(@Valid @ModelAttribute("bus") Bus bus,
                         BindingResult result,
                         Model model) {
        if (result.hasErrors()) {
            model.addAttribute("routes", routeService.getAllRoutes());
            return "admin/add-bus";
        }
        busService.saveBus(bus);
        return "redirect:/admin/buses";
    }


    @GetMapping("/admin/buses/edit/{id}")
    public String showEditBusForm(@PathVariable Long id, Model model) {
        Bus bus = busService.getBusById(id)
                .orElseThrow(() -> new RuntimeException("Bus not found"));
        model.addAttribute("bus", bus);
        model.addAttribute("routes", routeService.getAllRoutes());
        return "admin/edit-bus"; // renders templates/admin/edit-bus.html
    }


    @PostMapping("/admin/buses/edit/{id}")
    public String editBus(@PathVariable Long id,
                          @Valid @ModelAttribute("bus") Bus bus,
                          BindingResult result,
                          Model model) {
        if (result.hasErrors()) {
            model.addAttribute("routes", routeService.getAllRoutes());
            return "admin/edit-bus";
        }
        bus.setBusId(id);
        busService.updateBus(bus);
        return "redirect:/admin/buses";
    }

    @GetMapping("/admin/buses/delete/{id}")
    public String deleteBus(@PathVariable Long id) {
        busService.deleteBus(id);
        return "redirect:/admin/buses";
    }
}
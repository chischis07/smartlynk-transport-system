package com.example.transport_system.controller;

import com.example.transport_system.entity.Booking;
import com.example.transport_system.service.BookingService;
import com.example.transport_system.service.BusService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@Controller
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private BusService busService;


    @GetMapping("/book/{busId}")
    public String showBookingForm(@PathVariable Long busId, Model model) {
        // Pass the bus details to the form so the user can see what they're booking
        model.addAttribute("bus", busService.getBusById(busId)
                .orElseThrow(() -> new RuntimeException("Bus not found")));
        model.addAttribute("booking", new Booking());
        return "booking"; // renders templates/booking.html
    }


    @PostMapping("/book/{busId}")
    public String createBooking(@PathVariable Long busId,
                                @RequestParam Long userId,
                                @RequestParam Integer seatNumber,
                                Model model) {
        try {
            Booking booking = bookingService.createBooking(userId, busId, seatNumber);
            model.addAttribute("booking", booking);
            return "booking-confirmation"; // renders templates/booking-confirmation.html
        } catch (RuntimeException e) {
            model.addAttribute("errorMessage", e.getMessage());
            model.addAttribute("bus", busService.getBusById(busId).orElse(null));
            model.addAttribute("booking", new Booking());
            return "booking";
        }
    }


    @GetMapping("/user/bookings")
    public String userBookingHistory(@RequestParam Long userId,
                                     @RequestParam(defaultValue = "0") int page,
                                     Model model) {
        Page<Booking> bookingPage = bookingService.getBookingsByUserIdPaged(userId, page, 5);
        model.addAttribute("bookingPage", bookingPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", bookingPage.getTotalPages());
        model.addAttribute("userId", userId);
        return "booking-history"; // renders templates/booking-history.html
    }


    @GetMapping("/booking/cancel/{bookingId}")
    public String cancelBooking(@PathVariable Long bookingId,
                                @RequestParam Long userId) {
        bookingService.cancelBooking(bookingId);
        return "redirect:/user/bookings?userId=" + userId;
    }


    @GetMapping("/admin/bookings")
    public String adminBookingList(@RequestParam(defaultValue = "0") int page,
                                   @RequestParam(defaultValue = "") String search,
                                   Model model) {
        if (!search.isEmpty()) {
            model.addAttribute("bookings", bookingService.searchByPassengerName(search));
            model.addAttribute("searchTerm", search);
            return "admin/bookings";
        }

        Page<Booking> bookingPage = bookingService.getAllBookingsPaged(page, 10);
        model.addAttribute("bookingPage", bookingPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", bookingPage.getTotalPages());
        model.addAttribute("totalBookings", bookingService.getTotalBookings());
        return "admin/bookings"; // renders templates/admin/bookings.html
    }

    @GetMapping("/admin/bookings/delete/{id}")
    public String deleteBooking(@PathVariable Long id) {
        bookingService.deleteBooking(id);
        return "redirect:/admin/bookings";
    }
}
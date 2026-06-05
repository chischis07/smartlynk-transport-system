package com.example.transport_system.service;

import com.example.transport_system.entity.Booking;
import com.example.transport_system.entity.Bus;
import com.example.transport_system.entity.User;
import com.example.transport_system.repository.BookingRepository;
import com.example.transport_system.repository.BusRepository;
import com.example.transport_system.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BusRepository busRepository;

    /**
     * Create a new booking.
     * - Checks that the user and bus exist
     * - Checks that the bus is available
     * - Sets the price from the bus's route
     * - Marks the bus as unavailable after booking
     */
    public Booking createBooking(Long userId, Long busId, Integer seatNumber) {
        // Fetch the user - throw error if not found
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));

        // Fetch the bus - throw error if not found
        Bus bus = busRepository.findById(busId)
                .orElseThrow(() -> new RuntimeException("Bus not found with ID: " + busId));

        // Prevent double-booking an unavailable bus
        if (!bus.isAvailable()) {
            throw new RuntimeException("This bus is no longer available for booking");
        }

        // Create the booking object
        Booking booking = new Booking();
        booking.setUser(user);
        booking.setBus(bus);
        booking.setSeatNumber(seatNumber);
        booking.setStatus("ACTIVE");
        booking.setBookingDate(LocalDateTime.now());

        // Set price from the route if the bus has a route assigned
        if (bus.getRoute() != null) {
            booking.setAmountPaid(bus.getRoute().getPrice());
        }

        // Mark bus as unavailable now that it's booked
        bus.setAvailable(false);
        busRepository.save(bus);

        return bookingRepository.save(booking);
    }

    /**
     * Cancel a booking by ID.
     * Sets status to CANCELLED and frees up the bus.
     */
    public Booking cancelBooking(Long bookingId) {
        // Fix from original: used busRepository instead of bookingRepository
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found with ID: " + bookingId));

        booking.setStatus("CANCELLED");

        // Free the bus back up
        Bus bus = booking.getBus();
        bus.setAvailable(true);
        busRepository.save(bus);

        return bookingRepository.save(booking);
    }

    /**
     * Get all active bookings.
     */
    public List<Booking> getActiveBookings() {
        return bookingRepository.findAllActiveBookings();
    }

    /**
     * Get all bookings for a specific user.
     */
    public List<Booking> getBookingsByUserId(Long userId) {
        return bookingRepository.findByUserId(userId);
    }

    /**
     * Get bookings for a user with pagination (for booking history page).
     */
    public Page<Booking> getBookingsByUserIdPaged(Long userId, int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("bookingDate").descending());
        return bookingRepository.findByUserIdPageable(userId, pageable);
    }


    public Page<Booking> getAllBookingsPaged(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("bookingDate").descending());
        return bookingRepository.findAll(pageable);
    }


    public List<Booking> searchByPassengerName(String name) {
        return bookingRepository.searchByPassengerName(name);
    }


    public List<Booking> getRecentBookings() {
        LocalDateTime weekAgo = LocalDateTime.now().minusDays(7);
        return bookingRepository.getRecentBookings(weekAgo);
    }

    public long getTotalBookings() {
        return bookingRepository.getTotalBookings();
    }


    public List<Object[]> getMostBookedRoutes() {
        return bookingRepository.getMostBookedRoutes();
    }

    /**
     * Find a booking by ID.
     */
    public Optional<Booking> getBookingById(Long id) {
        return bookingRepository.findById(id);
    }

    /**
     * Delete a booking (admin only).
     */
    public void deleteBooking(Long id) {
        if (!bookingRepository.existsById(id)) {
            throw new RuntimeException("Booking not found with ID: " + id);
        }
        bookingRepository.deleteById(id);
    }
}
package com.example.transport_system.repository;

import com.example.transport_system.entity.Booking;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    // Find all bookings that are currently ACTIVE
    @Query("SELECT b FROM Booking b WHERE b.status = 'ACTIVE'")
    List<Booking> findAllActiveBookings();

    @Query("select s from Booking s where s.Bookingid = :id")
    public List<Booking>searchById(@Param("id")Long id);

    // Find all bookings for a specific user ID
    @Query("SELECT b FROM Booking b WHERE b.user.id = :userId")
    List<Booking> findByUserId(@Param("userId") Long userId);

    @Query("select s from Booking s where s.bookingDate = :date")
    public List<Booking>searchByDate(@Param("date") LocalDateTime bookingDate);

    // Count total number of bookings (for admin dashboard stats)
    @Query("SELECT COUNT(b) FROM Booking b")
    long getTotalBookings();

    @Query("select s from Booking s where s.status = :status")
    public List<Booking>searchByStatus(@Param("status")String Status);

    // Get all bookings with pagination (for booking history page)
    Page<Booking> findAll(Pageable pageable);

    // Get bookings for a specific user with pagination (for user booking history)
    @Query("SELECT b FROM Booking b WHERE b.user.id = :userId")
    Page<Booking> findByUserIdPageable(@Param("userId") Long userId, Pageable pageable);

    // Search bookings by passenger (user) first name
    @Query("SELECT b FROM Booking b WHERE LOWER(b.user.firstname) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Booking> searchByPassengerName(@Param("name") String name);

    // Get most booked routes, ordered by booking count descending
    @Query("SELECT b.bus.route, COUNT(b) AS total FROM Booking b GROUP BY b.bus.route ORDER BY total DESC")
    List<Object[]> getMostBookedRoutes();

    // Get bookings made in the last 7 days
    @Query("SELECT b FROM Booking b WHERE b.bookingDate >= :weekAgo ORDER BY b.bookingDate DESC")
    List<Booking> getRecentBookings(@Param("weekAgo") java.time.LocalDateTime weekAgo);



}

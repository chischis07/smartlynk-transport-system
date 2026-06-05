package com.example.transport_system.repository;

import com.example.transport_system.entity.Bus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import java.util.List;

public interface BusRepository extends JpaRepository<Bus, Long> {
    // Searches for a bus by its busNumber
    @Query("SELECT b FROM Bus b WHERE b.busNumber = :busNumber")
    List<Bus> searchByBusNumber(@Param("busNumber") Integer busNumber);

    // Searches for a bus based on its specific capacity entered by the admin
    @Query("select b from Bus b where b.capacity >= :capacity")
    public List<Bus>searchByCapacity(@Param("capacity")int capacity);

    // Implements pagination for admin list
    Page<Bus> findAll(Pageable pageable);

    // Checks for all buses that are available
    @Query("SELECT b FROM Bus b WHERE b.available = true")
    List<Bus> findAvailableBuses();

    // Searches for buses by their route ID
    @Query("SELECT b FROM Bus b WHERE b.route.id = :routeId")
    List<Bus> findByRouteId(@Param("routeId") Long routeId);

    // Find available buses on a specific route
    @Query("SELECT b FROM Bus b WHERE b.route.id = :routeId AND b.available = true")
    List<Bus> findAvailableBusesByRoute(@Param("routeId") Long routeId);


    // Search buses by route destination
    @Query("SELECT b FROM Bus b WHERE LOWER(b.route.destination) LIKE LOWER(CONCAT('%', :destination, '%'))")
    List<Bus> searchByDestination(@Param("destination") String destination);

}

package com.example.transport_system.repository;

import com.example.transport_system.entity.Route;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RouteRepository extends JpaRepository<Route, Long> {
    // Searches for a route by its route ID
    @Query("select r from Route r where r.Routeid = :routeid" )
    public List<Route> searchById(@Param("routeid") Long Id);

    // Searches for a route that starts from a particular origin
    @Query("select r from Route r where LOWER(r.origin) LIKE LOWER(CONCAT('%', origin, '%')) ")
    public List<Route>searchByOrigin(@Param("origin")String origin);

    // Searches for routes within a distance range
    @Query("SELECT r FROM Route r WHERE r.distance <= :maxDistance")
    List<Route> findByMaxDistance(@Param("maxDistance") Double maxDistance);

    // Searches for a route that ends at a particular destination
    @Query("select r from Route r where r.destination LIKE LOWER(CONCAT('%',destination, '%'))")
    public List<Route>searchByDestination(@Param("destination")String destination);

    // Searches for a specific route by exact origin AND an exact destination
    @Query("SELECT r FROM Route r WHERE r.origin = :origin AND r.destination = :destination")
    Optional<Route> findByOriginAndDestination(
            @Param("origin") String origin,
            @Param("destination") String destination
    );

    // Get all routes with pagination (for admin route management)
    Page<Route>findAll(Pageable pageable);


}

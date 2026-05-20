package repository;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface Route extends JpaRepository<Entity.Route, Long> {
    @Query("SELECT r FROM Route r WHERE r.destination LIKE %:destination%")
    List<Entity.Route> searchByDestination(@Param("destination") String destination);

    @Query("SELECT r FROM r WHERE r.origin = :origin AND r.destination = :destination")
    Optional<Entity.Route> findByOriginAndDestination(
            @Param("origin") String origin,
            @Param("destination") String destination
    );
}

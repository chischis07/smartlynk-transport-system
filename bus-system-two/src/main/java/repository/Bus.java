package repository;

import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface Bus extends JpaRepository<Entity.Bus, Long> {
    @Query("SELECT b FROM Bus b WHERE b.available = true")
    List<Entity.Bus> findAvailableBuses();

    @Query("""
            SELECT b FROM Bus b WHERE b.available = true
            AND b.id IN (
            SELECT bk.bus.id FROM Booking bk
            WHERE bk.route.id = :routeId
            AND DATE(bk.departureDate) = :date
            AND bk.status = 'ACTIVE'
            )
        """)
    List<Entity.Bus> findAvailableBusesByRouteAndDate(
            @Param("routeId") Long routeId,
            @Param("date") LocalDate date
            );
}

package repository;

import org.springframework.data.repository.reactive.RxJava3CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface Booking extends Jav.RepositoryRepository<Entity.Booking, Long> {
    @Query("SELECT b FROM Booking b WHERE b.status = 'ACTIVE'")
    List<Entity.Booking> findAllActiveBookings();

    @Query("SELECT b FROM Booking b WHERE b.passenger.fullName LIKE %:name%")
    List<Entity.Booking> searchByPassengerName(@Param("name") String name);

    @Query("SELECT b FROM Booking b WHERE b.route.destination = :destination")
    List<Entity.Booking> findByDestination(@Param("destination") String destination);

    @Query("SELECT b FROM Booking b WHERE DATE(b.departureDate) = :date")
    List<Entity.Booking> filterByDepartureDate(@Param("date")LocalDate date);

    @Query("SELECT COUNT(b) FROM Booking b")
    long getTotalBookings();

    @Query("SELECT b.route, COUNT(b) as total FROM Booking b GROUP BY b.route ORDER BY total DESC")
    List<Object[]> getMostBookedRoutes();

    @Query("SELECT b FROM Booking b WHERE b.departureDate > :weekAgo ORDER BY b.departureDate DESC")
    List<Entity.Booking> getRecentBookings(@Param("weekAgo")LocalDateTime weekAgo);

}

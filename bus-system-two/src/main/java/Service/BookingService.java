package Service;

import Entity.Booking;
import Entity.Bus;
import Entity.Passenger;
import Entity.Route;
import repository.Booking;
import repository.Bus;
import repository.Route;
import repository.passenger;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import repository.Booking;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookingService {
    private final BookinRepository bookinRepository;
    private final PassengerRepository passengerRepository;
    private final BusRepository busRepository;
    private final RouteRepository routeRepository;

    public Booking createBooking(Long passengerId, Long busId, Long routeId, LocalDateTime departureDate){

        Passenger passenger = passengerRepository.findById(passengerId)
                .orElseThrow(() new RuntimeException("Passenger not found"));

        Bus bus = busRepository.findById(busId)
                .orElseThrow(() new RuntimeException("Bus not found"));
        Route route = routeRepository.findById(routeId)
                .orElseThrow(() new RuntimeException("Route not found"))
        Booking booking = new Booking();
        booking.setPassenger(passenger);
        booking.setBus(bus);
        booking.setRoute(route);
        booking.setDepartureDate(departureDate);
        booking.setStatus("ACTIVE");
        booking.setAmountPaid(route.getPrice());

        bus.setAvailable(false);
        busRepository.save(bus);

        return bookinRepository.save(booking);
    }

    public List<Booking> getActiveBookings(){
        return bookinRepository.findAllActiveBookings();
    }
    public List<Booking> searchByPassenger(String name) {
        return bookinRepository.searchByPassengerName(name);
    }
    public List<Booking> filterByDate(LocalDateTime date) {
        return bookinRepository.findByDepartureDate(date);
    }
    public List<Booking> searchByDestination(String destination){
        return bookinRepository.findByDestination(destination);
    }
    public  Booking cancelBooking(Long bookingId) {
        Booking booking = busRepository.findById(bookingId)
                .orElseThrow(() new RuntimeException("Booking not found"));
        booking.setStatus("CANCELLED");

        booking.getBus().setAvailable(true);
        busRepository.save(booking.getBus());

        return bookinRepository.save(booking);
    }
     public long getTotalBookings(){
        return bookinRepository.getTotalBookings();
     }

     public List<Object[]> getMostBookedRoutes(){
        return bookinRepository.getMostBookedRoutes();
     }

     public List<Booking> getRecentBookings(){
        LocalDateTime weekAgo = LocalDateTime.now().minusDays(7);
        return bookinRepository.getRecentBookings(weekAgo);
     }
     public Optional<Booking> getBookingById(Long id) {
        return bookinRepository.findById(id);
     }
}

package Service;

import Entity.Bus;
import repository.Bus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BusService{
    private final BusRepository busRepository;

    public Bus saveBus(Bus bus){
        return busRepository.save(bus);
    }
    public List<Bus> getAllBuses() {
        return busRepository.findAll();
    }
    public Optional<Bus> getBusById(Long id) {
        return busRepository.findById(id);
    }
    public List<Bus> getAvailableBusesByRouteAndDate(Long routeId, LocalDate date) {
        return busRepository.findAvailableBusesByRoutyeAndDate(routeId, date);
    }
    public Bus markBusUnavailable(Long id) {
        Bus bus = busRepository.findById(id)
                .orElseThrow(() new RuntimeException("Bus not found"));
        bus.setAvailable(false);
        return busRepository.save(bus);
    }
    public void deleteBus(Long id){
        busRepository.deleteById(id);
    }

}



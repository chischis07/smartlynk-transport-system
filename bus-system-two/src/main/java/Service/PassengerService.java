package Service;


import Entity.Passenger;
import repository.passenger;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PassengerService{
        private final PassengerRepository passengerRepository;
        public Passenger savePassenger(Passenger passenger){
            return passengerRepository.save(passenger);
        }
        public List<Passenger> getAllPassengers() {
                return passengerRepository.findAll();
        }

        public Optional<Passenger> getPassengerById(Long id) {
            return passengerRepository.findById(id);
        }

        public List<Passenger> searchByName(String name) {
            return passengerRepository.searchByName(name);
        }

        public void deletePassenger(Long id) {
            passengerRepository.deleteById(id);
        }
   }




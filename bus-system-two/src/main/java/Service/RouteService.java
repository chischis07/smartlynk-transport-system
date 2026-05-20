package Service;

import Entity.Route;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RouteService {
    private final RouteRepository routeRepository;

    public Route saveRoute(Route route) {
        return routeRepository.save(route);
    }

    public List<Route> getAllRoutes(){
        return routeRepository.findAll();
    }
    public Optional<Route> getRouteById(Long id) {
        return routeRepository.findById(id);
    }
    public List<Route> searchByDestination(String destination) {
        return routeRepository.searchByDestination(destination);
    }
    public Optional<Route> findSpecificRoute(String origin, String destination) {
        return routeRepository.findByOriginAndDestination(origin, destination);
    }
    public void deleteRoute(Long id) {
        routeRepository.deleteById(id);
    }
}

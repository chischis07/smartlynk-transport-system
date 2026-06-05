package com.example.transport_system.service;

import com.example.transport_system.entity.Route;
import com.example.transport_system.repository.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class RouteService {

    @Autowired
    private RouteRepository routeRepository;

    public Route saveRoute(Route route) {
        return routeRepository.save(route);
    }

    public List<Route> getAllRoutes() {
        return routeRepository.findAll();
    }


    public Page<Route> getAllRoutesPaged(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("origin").ascending());
        return routeRepository.findAll(pageable);
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

    public Route updateRoute(Route route) {
        if (!routeRepository.existsById(route.getRouteId())) {
            throw new RuntimeException("Route not found with ID: " + route.getRouteId());
        }
        return routeRepository.save(route);
    }

    public void deleteRoute(Long id) {
        if (!routeRepository.existsById(id)) {
            throw new RuntimeException("Route not found with ID: " + id);
        }
        routeRepository.deleteById(id);
    }
}
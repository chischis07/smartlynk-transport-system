package com.example.transport_system.service;

import com.example.transport_system.entity.Bus;
import com.example.transport_system.repository.BusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class BusService {

    @Autowired
    private BusRepository busRepository;


    public Bus saveBus(Bus bus) {
        return busRepository.save(bus);
    }

    public List<Bus> getAllBuses() {
        return busRepository.findAll();
    }


    public Page<Bus> getAllBusesPaged(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("busName").ascending());
        return busRepository.findAll(pageable);
    }


    public Optional<Bus> getBusById(Long id) {
        return busRepository.findById(id);
    }


    public List<Bus> getAvailableBuses() {
        return busRepository.findAvailableBuses();
    }


    public List<Bus> getAvailableBusesByRoute(Long routeId) {
        return busRepository.findAvailableBusesByRoute(routeId);
    }


    public List<Bus> searchByDestination(String destination) {
        return busRepository.searchByDestination(destination);
    }


    public Bus markBusUnavailable(Long id) {
        Bus bus = busRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bus not found with ID: " + id));
        bus.setAvailable(false);
        return busRepository.save(bus);
    }


    public Bus markBusAvailable(Long id) {
        Bus bus = busRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bus not found with ID: " + id));
        bus.setAvailable(true);
        return busRepository.save(bus);
    }

    public Bus updateBus(Bus bus) {
        if (!busRepository.existsById(bus.getBusId())) {
            throw new RuntimeException("Bus not found with ID: " + bus.getBusId());
        }
        return busRepository.save(bus);
    }


    public void deleteBus(Long id) {
        if (!busRepository.existsById(id)) {
            throw new RuntimeException("Bus not found with ID: " + id);
        }
        busRepository.deleteById(id);
    }
}
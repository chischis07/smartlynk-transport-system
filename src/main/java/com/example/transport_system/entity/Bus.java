package com.example.transport_system.entity;
import com.example.transport_system.entity.Route;
import com.example.transport_system.entity.Booking;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "Bus")
public class Bus {
    @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long busId;

    @NotBlank(message = "Bus name is required")
    @Column(nullable = false)
    private String busName;

    @NotNull(message = "Bus number is required")
    @Column(nullable = false, unique = true)
    private Integer busNumber;

    @NotNull(message = "Capacity is required")
    @Min(value = 1, message = "Capacity must be at least 1")
    @Column(nullable = false)
    private Integer capacity;

    private boolean available = true;



    // Entity relationships
    @ManyToOne
    @JoinColumn(name = "Routeid")
    private Route route;

    @OneToMany(mappedBy = "bus", cascade = CascadeType.ALL)
    private List<Booking> bookings;



    // Constructors
    public Bus(String busName, Long busId, Integer busNumber, Integer capacity, String name, Route route, List<Booking> bookings) {
        this.busId = busId;
        this.busNumber = busNumber;
        this.capacity = capacity;
        this.busName = busName;
        this.route = route;
        this.bookings = bookings;
    }
    public Bus(){}

    public String getBusName() {
        return busName;
    }

    public void setBusName(String busName) {
        this.busName = busName;
    }

    public Long getBusId() {
        return busId;
    }

    public void setBusId(Long id) {
        this.busId = id;

    }

    public int getBusNumber() {
        return busNumber;
    }

    public void setBusNumber(int busNumber) {
        this.busNumber = busNumber;

    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;

    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }


    public void setRoute(Route route) {
        this.route = route;

    }

    public Route getRoute() {
        return route;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public Bus setBookings(List<Booking> bookings) {
        this.bookings = bookings;
        return this;
    }
}

package com.example.transport_system.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.List;

@Entity
@Table(name = "Route")
public class Route{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Routeid;

    @NotBlank(message = "Origin is required")
    @Column(nullable = false)
    private String origin;

    @NotBlank(message = "Destination is required")
    @Column(nullable = false)
    private String destination;

    @NotNull(message = "Distance is required")
    @Positive(message = "Distance must be a positive number")
    private double distance;

    @NotNull(message = "Price is required")
    @Positive(message = "Price must be a positive number")
    private double price;


    // Entity Relationship
    @OneToMany(mappedBy = "route", cascade = CascadeType.ALL)
    private List<Bus> buses;

    public Route(Long id, String origin, String destination, double distance, List<Bus> buses, double price) {
        this.Routeid = id;
        this.origin = origin;
        this.destination = destination;
        this.distance = distance;
        this.buses = buses;
        this.price = price;

    }

    public Route() {
    }

    public Long getRouteId() {
        return Routeid;
    }

    public void setRouteId(Long id) {
        this.Routeid = id;

    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;

    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;

    }

    public List<Bus> getBuses() {
        return buses;
    }

    public void setBuses(List<Bus> buses) {
        this.buses = buses;

    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;

    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}

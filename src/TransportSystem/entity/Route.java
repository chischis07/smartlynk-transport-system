package com.example.TransportSystem.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Route")
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private double Routeid;
    private String origin;
    private String destination;
    private double distance;
    @OneToMany(mappedBy = "Route")
    private List<Bus> buses;

    public Route(double id, String origin, String destination, double distance, List<Bus> buses) {
        this.Routeid = id;
        this.origin = origin;
        this.destination = destination;
        this.distance = distance;
        this.buses = buses;
    }

    public Route() {
    }

    public double getRouteId() {
        return Routeid;
    }

    public void setRouteId(double id) {
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
}

package com.example.TransportSystem.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "Bus")
public class Bus {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private double Busid;
    private int BusNumber;
    private int capacity;
    @ManyToOne
    @JoinColumn(name = "Routeid")
    private Route route;
    @OneToMany(mappedBy = "Bus")
    @JoinColumn(name = "Bookingid")
    private List<Booking> bookings;

    public Bus(double id, int busNumber, int capacity, String name, Route route, List<Booking> bookings) {
        this.Busid = id;
        this.BusNumber = busNumber;
        this.capacity = capacity;

        this.route = route;
        this.bookings = bookings;
    }
    public Bus(){}

    public double getBusId() {
        return Busid;
    }

    public void setBusId(double id) {
        this.Busid = id;

    }

    public int getBusNumber() {
        return BusNumber;
    }

    public void setBusNumber(int busNumber) {
        this.BusNumber = busNumber;

    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;

    }



    public Route route() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;

    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public Bus setBookings(List<Booking> bookings) {
        this.bookings = bookings;
        return this;
    }
}

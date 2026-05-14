package com.example.TransportSystem.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;



@Entity
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private double Bookingid;
    private int SeatNumber;
    private LocalDateTime bookingDate;
    private String Status;
    @ManyToOne
    @JoinColumn(name = "Userid")
    private User user;
    @ManyToOne
    @JoinColumn(name = "Busid")
    private Bus bus;

    public Booking(double id, int seatNumber, LocalDateTime bookingDate, String status, User user, Bus bus) {
        this.Bookingid = id;
        SeatNumber = seatNumber;
        this.bookingDate = bookingDate;
        Status = status;
        this.user = user;
        this.bus = bus;

    }
    public Booking(){}

    public double getBookingId() {
        return Bookingid;
    }

    public void setBookingId(double id) {
        this.Bookingid = id;

    }

    public int getSeatNumber() {

        return SeatNumber;
    }

    public void setSeatNumber(int seatNumber) {
       this.SeatNumber = seatNumber;

    }

    public LocalDateTime getBookingDatebooking() {

        return bookingDate;
    }

    public void setBookingDate(LocalDateTime bookingDate) {
        this.bookingDate = bookingDate;

    }

    public String Status() {
        return Status;
    }

    public void setStatus(String status) {
       this. Status = status;

    }

    public User user() {

        return user;
    }

    public void setUser(User user) {
        this.user = user;

    }

    public Bus bus() {

        return bus;
    }

    public void setBus(Bus bus) {
        this.bus = bus;

    }
}



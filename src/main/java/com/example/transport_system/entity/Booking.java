package com.example.transport_system.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;



@Entity
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Bookingid;

    @NotNull(message = "Seat number is required")
    @Min(value = 1, message = "Seat number must be at least 1")
    @Column(nullable = false)
    private Integer SeatNumber;

    @Column(updatable = false)
    private LocalDateTime bookingDate;

    @NotBlank(message = "Status is required")
    private String status;


    private Double amountPaid;


    // Entity relationships
    @ManyToOne
    @JoinColumn(name = "Userid", nullable = false)
    private User user;



    @ManyToOne
    @JoinColumn(name = "Busid", nullable = false)
    private Bus bus;


    // Constructors
    public Booking(Long id, Integer seatNumber, LocalDateTime bookingDate, String status, User user, Bus bus, double amountPaid) {
        this.Bookingid = id;
        SeatNumber = seatNumber;
        this.bookingDate = bookingDate;
        status = status;
        this.user = user;
        this.bus = bus;
        this.amountPaid = amountPaid;

    }
    public Booking(){}

    public double getBookingId() {
        return Bookingid;
    }

    public void setBookingId(Long id) {
        this.Bookingid = id;

    }

    public int getSeatNumber() {

        return SeatNumber;
    }

    public void setSeatNumber(Integer seatNumber) {
       this.SeatNumber = seatNumber;

    }

    public LocalDateTime getBookingDatebooking() {

        return bookingDate;
    }

    public void setBookingDate(LocalDateTime bookingDate) {
        this.bookingDate = bookingDate;

    }

    public String Status() {
        return status;
    }

    public void setStatus(String status) {
       this. status = status;

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
    public Bus getBus() {
        return bus;
    }
    public void setBus(Bus bus) {
        this.bus = bus;

    }


    public Double getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(Double amountPaid) {
        this.amountPaid = amountPaid;
    }
}



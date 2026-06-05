package com.example.transport_system.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Entity
@Table(name = "Payment")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Paymentid;

    @NotNull(message = "Amount is required")
    @Positive(message = "Amount must be greater than 0")
    @Column(nullable = false)
    private double amount;

    @NotBlank(message = "Payment status is required")
    private String PaymentStatus;


    // Entity relationship
    @OneToOne
    @JoinColumn(name = "Bookingid", nullable = false)
    private Booking booking;

    public Payment(Long id, Booking booking, String paymentStatus, double amount) {
        this.Paymentid = id;
        this.booking = booking;
        this.PaymentStatus = paymentStatus;
        this.amount = amount;
    }

    public Payment() {
    }

    public double getPaymentid() {
        return Paymentid;
    }

    public void setPaymentId(Long id) {
        this.Paymentid = id;

    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;

    }

    public String getPaymentStatus() {
        return PaymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
       this. PaymentStatus = paymentStatus;

    }

    public Booking getBookingbooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;

    }
}

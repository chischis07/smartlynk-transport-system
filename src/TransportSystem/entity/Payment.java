package com.example.TransportSystem.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Payment")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private double Paymentid;
    private double amount;
    private String PaymentStatus;

    @OneToOne
    @JoinColumn(name = "Bookingid")
    private Booking booking;

    public Payment(double id, Booking booking, String paymentStatus, double amount) {
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

    public void setPaymentId(double id) {
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

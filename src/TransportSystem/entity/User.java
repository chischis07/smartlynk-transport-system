package com.example.TransportSystem.entity;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "User")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
        private double Userid;
        private String firstname;
        private String lastname;
        @Column(unique = true)
        private String email;
        private String password;

        @OneToMany(mappedBy = "User")
        private List<Booking> bookings;

    public User(double id, String firstname, String lastname, String email, String password, List<Booking> bookings) {
        this.Userid = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.bookings = bookings;
    }
    public User(){}

    public double getUserid() {
        return Userid;
    }

    public User setUserId(double id) {
        this.Userid = id;
        return this;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;

    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;

    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
         
    }
}



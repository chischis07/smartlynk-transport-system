package com.example.transport_system.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import com.example.transport_system.entity.Booking;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
@Table(name = "User")
public class User {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long Userid;



    @Enumerated(EnumType.STRING)
        private Role role;

        @NotBlank(message = "First name is required")
        private String firstname;

        @NotBlank(message = "Last name is required")
        private String lastname;

        @Column(unique = true)
        @NotBlank(message = "Email is required")
        @Email(message = "Enter a valid email address")
        private String email;

        @NotBlank(message = "Password is required")
        @Size(min = 6, message = "Password must be at least 6 characters")
        private String password;



        // Entity relationships
        @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
        private List<Booking> bookings;

    public User(Long id, String firstname, String lastname, String email, String password, List<Booking> bookings) {
        this.Userid = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.bookings = bookings;
    }
    public User(){}

    public Long getUserid() {
        return Userid;
    }

    public User setUserId(Long id) {
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

}



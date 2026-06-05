package com.example.transport_system.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Adminid;

    @NotBlank(message = "Username is required")
    @Column(unique = true, nullable = false)
    private String username;

    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password must be at least 6 characters")
    private String password;

    public Admin(Long id, String username, String password) {
        this.Adminid = id;
        this.username = username;
        this.password = password;
    }
    public Admin(){}

    public Long getAdminId() {
        return Adminid;
    }

    public void  setAdminId(Long id) {
        this.Adminid = id;

    }

    public String getUsername() {
        return username;
    }

    public void  setUsername(String username) {
        this.username = username;

    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;

    }
}

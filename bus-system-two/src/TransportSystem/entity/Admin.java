package com.example.TransportSystem.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Admin {
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
    private double Adminid;
    private String username;
    private String password;

    public Admin(double id, String username, String password) {
        this.Adminid = id;
        this.username = username;
        this.password = password;
    }
    public Admin(){}

    public double getAdminId() {
        return Adminid;
    }

    public void  setAdminId(double id) {
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

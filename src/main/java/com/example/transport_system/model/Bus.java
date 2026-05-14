package com.example.transport_system.model;

import jakarta.persistence.*;
        import jakarta.validation.constraints.*;

@Entity
public class Bus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Bus name is required")
    private String busName;

    @NotBlank(message = "Plate number is required")
    private String plateNumber;

    @NotNull(message = "Total seats is required")
    @Min(value = 1, message = "Seats must be at least 1")
    private Integer totalSeats;

    @NotBlank(message = "Route is required")
    private String route;

    // getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBusName() {
        return busName;
    }

    public void setBusName(String busName) {
        this.busName = busName;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public Integer getTotalSeats() {
        return totalSeats;
    }

    public void setTotalSeats(Integer totalSeats) {
        this.totalSeats = totalSeats;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }
}
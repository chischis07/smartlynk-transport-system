package com.example.TransportSystem.UserRepository;

import com.example.TransportSystem.entity.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RouteRepository extends JpaRepository<Route, Double> {
    @Query("select r from Route r where r.Routeid:=routeid" )
    public List<Route> searchById(@Param("routeid") double Id);

    @Query("select r from Route r where r.origin:=origin ")
    public List<Route>searchByOrigin(@Param("origin")String origin);

    @Query("select r from Route r where r.distance:=distance")
    public List<Route>searchByDistance(@Param("distance")double distance);

    @Query("select r from Route r where r.destination:=destination")
    public List<Route>searchByDestination(@Param("destination")String destination);


}

package com.example.TransportSystem.UserRepository;

import com.example.TransportSystem.entity.Bus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BusRepository extends JpaRepository<Bus, Double> {
    @Query("select b from Bus b where b.Busid:=busid")
    public List<Bus> searchById(@Param("busid")double id);

    @Query("select b from Bus b where b.BusNumber:=busnumber")
    public List<Bus>searchByBusnum(@Param("busnumber")int BusNumber);


    @Query("select b from Bus b where b.capacity:=capacity")
    public List<Bus>searchByCapacity(@Param("capacity")int capacity);
}

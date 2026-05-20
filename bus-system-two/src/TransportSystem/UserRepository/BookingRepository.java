package com.example.TransportSystem.UserRepository;

import com.example.TransportSystem.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Double> {
    @Query("select s from Booking s where s.Bookingid:=id")
    public List<Booking>searchById(@Param("id")double id);

    @Query("select s from Booking s where s.SeatNumber:=seatnum")
    public List<Booking>searchBySeatnum(@Param("seatnum")int SeatNumber);

    @Query("select s from Booking s where s.bookingDate:=date")
    public List<Booking>searchByDate(@Param("date") LocalDateTime bookingDate);

    @Query("select s from Booking s where s.Status:=status")
    public List<Booking>searchByStatus(@Param("status")String Status);


}

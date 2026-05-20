package com.example.TransportSystem.UserRepository;

import com.example.TransportSystem.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Double> {
    @Query("select p from Payment p where p.Paymentid:=payid")
    public List<Payment> searchById(@Param("payid")double Paymentid);

    @Query("select p from Payment p where p.PaymentStatus:= paymentstatus")
    public List<Payment>searchByPaymentStatus(@Param("paymentstatus")String PaymentStatus);
}

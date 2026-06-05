package com.example.transport_system.repository;

import com.example.transport_system.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    @Query("select p from Payment p where p.Paymentid = :Paymentid")
    public List<Payment> searchById(@Param("Paymentid")double Paymentid);

    @Query("select p from Payment p where p.PaymentStatus = :PaymentStatus")
    public List<Payment>searchByPaymentStatus(@Param("PaymentStatus")String PaymentStatus);
}

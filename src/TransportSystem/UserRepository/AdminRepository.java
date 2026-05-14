package com.example.TransportSystem.UserRepository;

import com.example.TransportSystem.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository

public interface AdminRepository extends JpaRepository<Admin,Double> {
    @Query("select a from Admin where a.Adminid:= adminId")
    public List<Admin> searchById(@Param( "adminId") double id);

    @Query("select a from Admin where a.username:=username")
    public List<Admin>searchByUsername(@Param("username")String username);



}

package com.example.transport_system.repository;

import com.example.transport_system.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository

public interface AdminRepository extends JpaRepository<Admin, Long> {

    @Query("SELECT a FROM Admin a WHERE LOWER(a.username) LIKE LOWER(CONCAT('%', :username, '%'))")
    public List<Admin>searchByUsername(@Param("username")String username);

    @Query("SELECT a FROM Admin a WHERE a.username = :username")
    Optional<Admin> findByUsername(@Param("username")String username);

    boolean existsByUsername(String username);

}

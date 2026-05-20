package repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface passenger extends JpaRepository<passenger, Long>
    @Query("SELECT p FROM Passenger p WHERE p.fullName LIKE%:name%")
    List<passenger> searchByName(@Param("name") String name);
    @Query("SELECT p FROM Passenger p WHERE p.email = :email")
    Optional<passenger> findByEmail(@Param("email") String email);
    @Query("SELECT p FROM Passenger p WHERE p.phone = :phone")
    Optional<passenger> findByPhone(@Param("name") String phone);{
        }

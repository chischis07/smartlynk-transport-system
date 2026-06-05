package com.example.transport_system.repository;

import com.example.transport_system.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    // Finding a user by their email address
    @Query("select u From User u where u.email = :email")
    Optional<User> findByEmail(@Param("email") String email);

    // Searches for a user whose first name contains the values entered by the admin. It's not case-sensitive because all entered and returned values will be transformed to lower case
    @Query("SELECT u FROM User u WHERE LOWER(u.firstname) LIKE LOWER(CONCAT('%', :firstname, '%'))")
    List<User> searchByFirstName(@Param("firstname") String firstname);

    // Searches for a user whose last name contains the values entered by the admin. It's not case-sensitive because all entered and returned values will be transformed to lower case
    @Query("select u From User u where u.lastname = LOWER(CONCAT ('%', :lastname, '%')) ")
    public List<User>searchByLastname(@Param("lastname") String lastname);

    // Implementing pagination for the return of results
    Page<User> findAll(Pageable pageable);

    boolean existsByEmail(String email);


}

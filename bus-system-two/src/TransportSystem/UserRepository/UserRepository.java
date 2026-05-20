package com.example.TransportSystem.UserRepository;

import com.example.TransportSystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Double> {
    @Query("select u From User u where u.Userid:=userId")
    public List<User> searchById(@Param("userId") double Id );


    @Query("select u From User u  where u.firstname:=firstname")
    public List<User> searchByFirstname(@Param("firstname") String firstname);

    @Query("select u From User u where u.lastname:=lastname")
    public List<User>searchByLastname(@Param("lastname") String lastname);

    @Query("select u From User u where u.email:= email")
    public List<User>searchByEmail(@Param("email")String email);

 

}

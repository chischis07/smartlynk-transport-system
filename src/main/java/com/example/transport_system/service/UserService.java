package com.example.transport_system.service;

import com.example.transport_system.entity.Role;
import com.example.transport_system.entity.User;
import com.example.transport_system.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User registerUser(User user) {
        user.setRole(Role.USER);
        // Prevent registering with an email already in the database
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("A user with this email already exists");
        }

        return userRepository.save(user);
    }



    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


    public Page<User> getAllUsersPaged(int pageNumber, int pageSize) {
        // Sort by lastName alphabetically
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("lastName").ascending());
        return userRepository.findAll(pageable);
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }


    public User updateUser(User user) {

        if (!userRepository.existsById(user.getUserid())) {
            throw new RuntimeException("User not found with ID: " + user.getUserid());
        }
        return userRepository.save(user);
    }


    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User not found with ID: " + id);
        }
        userRepository.deleteById(id);
    }

    public List<User> searchByFirstName(String firstName) {
        return userRepository.searchByFirstName(firstName);
    }

    public Optional<User> loginUser(String email, String password) {
        Optional<User> userOpt = userRepository.findByEmail(email);


        if (userOpt.isPresent()) {
            User user = userOpt.get();

            if (user.getPassword().equals(password)) {
                return Optional.of(user);
            }
        }

        return Optional.empty();
    }
}
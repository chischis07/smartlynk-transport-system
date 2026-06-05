package com.example.transport_system.service;

import com.example.transport_system.entity.Admin;
import com.example.transport_system.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;


    public Admin registerAdmin(Admin admin) {
        if (adminRepository.existsByUsername(admin.getUsername())) {
            throw new RuntimeException("An admin with this username already exists");
        }
        return adminRepository.save(admin);
    }

    public boolean loginAdmin(String username, String password) {
        Optional<Admin> admin = adminRepository.findByUsername(username);
        return admin.isPresent() && admin.get().getPassword().equals(password);
    }


    public Optional<Admin> findByUsername(String username) {
        return adminRepository.findByUsername(username);
    }


    public Optional<Admin> getAdminById(Long id) {
        return adminRepository.findById(id);
    }
}
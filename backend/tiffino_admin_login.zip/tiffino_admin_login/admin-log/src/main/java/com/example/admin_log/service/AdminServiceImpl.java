package com.example.admin_log.service;

import com.example.admin_log.entity.Admin;
import com.example.admin_log.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Primary
@RequiredArgsConstructor
@Service
public class AdminServiceImpl implements AdminService, UserDetailsService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Admin loadUserByEmail(String email) {
        return adminRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Admin not found with email: " + email));
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Admin admin = loadUserByEmail(email);
        return User.builder()
                .username(admin.getEmail())
                .password(admin.getPassword())
                .roles("ADMIN")
                .build();
    }


    @Override
    public boolean login(String email, String password) {
        Admin admin = adminRepository.findByEmail(email).orElse(null);
        return admin != null && passwordEncoder.matches(password, admin.getPassword());
    }


    @Override
    public String resetPassword(String email, String newPassword, String confirmPassword) {
        if (!newPassword.equals(confirmPassword)) {
            return "Passwords do not match!";
        }
        Admin admin = adminRepository.findByEmail(email).orElse(null);
        if (admin == null) return "Admin not found!";
        admin.setPassword(passwordEncoder.encode(newPassword));
        adminRepository.save(admin);
        return "Password reset successfully!";
    }
}

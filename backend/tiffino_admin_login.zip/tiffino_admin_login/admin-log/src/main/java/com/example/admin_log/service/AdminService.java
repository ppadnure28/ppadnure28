package com.example.admin_log.service;

import com.example.admin_log.entity.Admin;

public interface AdminService {

    boolean login(String email, String password);

    String resetPassword(String email, String newPassword, String confirmPassword);

    Admin loadUserByEmail(String email);
}

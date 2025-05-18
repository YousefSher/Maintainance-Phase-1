package com.learning_management_system.service;

import com.learning_management_system.entity.Admin;
import com.learning_management_system.entity.Users;
import com.learning_management_system.repository.AdminRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    private final AdminRepository adminRepository;

    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }
    public void save(int adminId, Admin admin, HttpServletRequest request) {
        Users loggedInInstructor = (Users) request.getSession().getAttribute("user");
        if (loggedInInstructor == null) {
            throw new IllegalArgumentException("User is not logged in.");
        }
        if(adminId != loggedInInstructor.getUserId()) {
            throw new IllegalArgumentException("You are not authorized to perform this action.");
        }
        Admin newAdmin=adminRepository.getReferenceById(adminId);
        newAdmin.setFirstName(admin.getFirstName());
        newAdmin.setLastName(admin.getLastName());
        adminRepository.save(newAdmin);
    }
}

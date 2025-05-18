package com.learning_management_system.controller;

import com.learning_management_system.dto.NotificationDto;
import com.learning_management_system.entity.Admin;
import com.learning_management_system.service.AdminService;
import com.learning_management_system.service.NotificationsService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    private final AdminService adminService;
    private final NotificationsService notificationsService;
    public AdminController(AdminService adminService, NotificationsService notificationsService) {
        this.adminService = adminService;
        this.notificationsService = notificationsService;
    }
    @PutMapping("/update_profile/{adminId}")
    public ResponseEntity<String> updateUser(@PathVariable int adminId,
                                             @RequestBody Admin admin,
                                             HttpServletRequest request
    ) {
        try {
            adminService.save(adminId, admin, request);
            return ResponseEntity.ok("Admin updated successfully.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/notifications/{userId}")
    public ResponseEntity<?> getAllNotifications(@PathVariable int userId, HttpServletRequest request) {
        try {
            List<NotificationDto> notificationDtoList = notificationsService.getAllNotificationsAdmin(userId, request);
            return ResponseEntity.ok(notificationDtoList);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/unreadnotifications/{userId}")
    public ResponseEntity<?> getUnreadNotifications(@PathVariable int userId,HttpServletRequest request) {
        try {
            List<NotificationDto> notificationDtoList = notificationsService.getAllUnreadNotificationsAdmin(userId, request);
            return ResponseEntity.ok(notificationDtoList);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}

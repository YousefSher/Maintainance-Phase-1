package com.learning_management_system.controller;
import com.learning_management_system.dto.NotificationDto;
import com.learning_management_system.entity.Instructor;
import com.learning_management_system.service.InstructorService;
import com.learning_management_system.service.NotificationsService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/instructor")
public class InstructorController {
    private final InstructorService instructorService;
    private final NotificationsService notificationsService;

    public InstructorController(InstructorService instructorService, NotificationsService notificationsService) {
        this.instructorService = instructorService;
        this.notificationsService = notificationsService;
    }
    @PutMapping("/update_profile/{instructorId}")
    public ResponseEntity<String> updateUser(@PathVariable int instructorId,
                                             @RequestBody Instructor instructor,
                                             HttpServletRequest request
    ) {
        try {
            instructorService.save(instructorId, instructor, request);
            return ResponseEntity.ok("Instructor updated successfully.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/notifications/{userId}")
    public ResponseEntity<?> getAllNotifications(@PathVariable int userId, HttpServletRequest request) {
        try {
            List<NotificationDto> notificationDtoList = notificationsService.getAllNotificationsInstructor(userId, request);
            return ResponseEntity.ok(notificationDtoList);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/unreadnotifications/{userId}")
    public ResponseEntity<?> getUnreadNotifications(@PathVariable int userId,HttpServletRequest request) {
        try {
            List<NotificationDto> notificationDtoList = notificationsService.getAllUnreadNotificationsInstructor(userId, request);
            return ResponseEntity.ok(notificationDtoList);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
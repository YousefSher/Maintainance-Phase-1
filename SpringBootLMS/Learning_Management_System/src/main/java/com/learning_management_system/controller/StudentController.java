package com.learning_management_system.controller;


import com.learning_management_system.dto.NotificationDto;
import com.learning_management_system.entity.Student;
import com.learning_management_system.service.NotificationsService;
import com.learning_management_system.service.StudentService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/student")
public class StudentController {
    private final StudentService studentService;
    private final NotificationsService notificationsService;
    public StudentController(StudentService studentService, NotificationsService notificationsService) {
        this.studentService = studentService;
        this.notificationsService = notificationsService;
    }
    @PutMapping("/update_profile/{studentId}")
    public ResponseEntity<String> updateUser(@PathVariable int studentId,
                           @RequestBody Student student,
                           HttpServletRequest request
    ) {
        try {
            studentService.save(studentId, student, request);
            return ResponseEntity.ok("Student updated successfully.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/notifications/{userId}")
    public ResponseEntity<?> getAllNotifications(@PathVariable int userId, HttpServletRequest request) {
        try {
            List<NotificationDto> notificationDtoList = notificationsService.getAllNotificationsStudent(userId, request);
            return ResponseEntity.ok(notificationDtoList);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/unreadnotifications/{userId}")
    public ResponseEntity<?> getUnreadNotifications(@PathVariable int userId,HttpServletRequest request) {
        try {
            List<NotificationDto> notificationDtoList = notificationsService.getAllUnreadNotificationsStudent(userId, request);
            return ResponseEntity.ok(notificationDtoList);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}

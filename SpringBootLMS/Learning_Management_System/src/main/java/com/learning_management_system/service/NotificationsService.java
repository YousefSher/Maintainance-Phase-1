package com.learning_management_system.service;

import com.learning_management_system.dto.NotificationDto;
import com.learning_management_system.entity.Notifications;
import com.learning_management_system.entity.Users;
import com.learning_management_system.repository.NotificationsRepository;
import com.learning_management_system.repository.UsersRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.catalina.filters.ExpiresFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class NotificationsService {

    private final NotificationsRepository notificationsRepository;
    private final UsersRepository usersRepository;

    @Autowired
    public NotificationsService(NotificationsRepository notificationsRepository, UsersRepository usersRepository) {
        this.notificationsRepository = notificationsRepository;
        this.usersRepository = usersRepository;
    }

    //For Instructor
    public List<NotificationDto> getAllNotificationsInstructor(int userId, HttpServletRequest request) {
        Users loggedInInstructor = (Users) request.getSession().getAttribute("user");
        if (loggedInInstructor == null) {
            throw new IllegalArgumentException("No user is logged in.");
        }
        if (loggedInInstructor.getUserTypeId() == null || loggedInInstructor.getUserTypeId().getUserTypeId() != 3) {
            throw new IllegalArgumentException("Logged-in user is not an instructor.");
        }
        if(userId != loggedInInstructor.getUserId()){
            throw new IllegalArgumentException("Logged-in user is an another instructor.");
        }
        return getAllNotifications(userId);
    }



    //For Student
    public List<NotificationDto> getAllNotificationsStudent(int userId, HttpServletRequest request) {
        Users loggedInStudent = (Users) request.getSession().getAttribute("user");
        if (loggedInStudent == null) {
            throw new IllegalArgumentException("No user is logged in.");
        }
        if (loggedInStudent.getUserTypeId() == null || loggedInStudent.getUserTypeId().getUserTypeId() != 2) {
            throw new IllegalArgumentException("Logged-in user is not a Student.");
        }
        if(userId != loggedInStudent.getUserId()){
            throw new IllegalArgumentException("Logged-in user is another student.");
        }
        return getAllNotifications(userId);
    }

    public List<NotificationDto> getAllNotificationsAdmin(int userId, HttpServletRequest request) {
        Users loggedInAdmin = (Users) request.getSession().getAttribute("user");
        if (loggedInAdmin == null) {
            throw new IllegalArgumentException("No user is logged in.");
        }
        if (loggedInAdmin.getUserTypeId() == null || loggedInAdmin.getUserTypeId().getUserTypeId() != 2) {
            throw new IllegalArgumentException("Logged-in admin is not a Student.");
        }
        if(userId != loggedInAdmin.getUserId()){
            throw new IllegalArgumentException("Logged-in user is another admin.");
        }
        return getAllNotifications(userId);
    }

    public List<NotificationDto> getAllNotifications(int userId) {
        List<Notifications> notificationsList = notificationsRepository.findAll();

        List<NotificationDto> notificationsDtoList = new ArrayList<>();

        for (Notifications notification : notificationsList) {
            if (notification.getUserId().getUserId() == userId) {
                notification.setRead(true);
                notificationsRepository.save(notification);

                NotificationDto notificationDto = new NotificationDto();
                notificationDto.setNotificationsId(notification.getNotificationsId());
                notificationDto.setUserId(notification.getUserId().getUserId());
                notificationDto.setMessage(notification.getMessage());
                notificationDto.setCreatedTime(notification.getCreatedTime());
                notificationDto.setRead(notification.isRead());

                notificationsDtoList.add(notificationDto);
            }
        }

        return notificationsDtoList;
    }

    public List<String> getAllUnreadNotifications(int userId) {
        List<Notifications> notificationsList = notificationsRepository.findAll();

        List<String> notificationsMessage = new ArrayList<>();

        for (Notifications notification : notificationsList) {
            if (notification.getUserId().getUserId() == userId && !notification.isRead()) {
                notification.setRead(true);
                notificationsMessage.add(notification.getMessage());
                notificationsRepository.save(notification);
            }
        }

        return notificationsMessage;
    }

    public void sendNotification(String message, int id) {
        Users user = usersRepository.findById(id).get();
        Notifications enrollmentNotification = new Notifications();
        enrollmentNotification.setUserId(user);
        enrollmentNotification.setRead(false);
        enrollmentNotification.setCreatedTime(new Date());
        enrollmentNotification.setMessage(message);
        notificationsRepository.save(enrollmentNotification);

    }


}

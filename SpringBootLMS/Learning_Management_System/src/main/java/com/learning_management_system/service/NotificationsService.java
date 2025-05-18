package com.learning_management_system.service;

import com.learning_management_system.entity.Notifications;
import com.learning_management_system.entity.Users;
import com.learning_management_system.repository.NotificationsRepository;
import com.learning_management_system.repository.UsersRepository;
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

    public List<String> getAllNotifications(int userId) {
        List<Notifications> notificationsList = notificationsRepository.findAll();

        List<String> notificationsMessage = new ArrayList<>();

        for (Notifications notification : notificationsList) {
            if (notification.getUserId().getUserId() == userId) {
                notification.setRead(true);
                notificationsMessage.add(notification.getMessage());
                notificationsRepository.save(notification);
            }
        }

        return notificationsMessage;
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

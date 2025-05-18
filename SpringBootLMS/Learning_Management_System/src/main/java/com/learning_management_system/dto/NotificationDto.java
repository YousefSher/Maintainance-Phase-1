package com.learning_management_system.dto;

import java.util.Date;

public class NotificationDto {
    private int notificationsId;
    private int userId; // Just the ID to avoid full user object
    private String message;
    private Date createdTime;
    private boolean read;

    public NotificationDto() {}

    public NotificationDto(int notificationsId, int userId, String message, Date createdTime, boolean read) {
        this.notificationsId = notificationsId;
        this.userId = userId;
        this.message = message;
        this.createdTime = createdTime;
        this.read = read;
    }

    public int getNotificationsId() {
        return notificationsId;
    }

    public void setNotificationsId(int notificationsId) {
        this.notificationsId = notificationsId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }

    @Override
    public String toString() {
        return "NotificationsDTO{" +
                "notificationsId=" + notificationsId +
                ", userId=" + userId +
                ", message='" + message + '\'' +
                ", createdTime=" + createdTime +
                ", read=" + read +
                '}';
    }
}
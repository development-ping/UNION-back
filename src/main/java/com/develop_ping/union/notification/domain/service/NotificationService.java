package com.develop_ping.union.notification.domain.service;

import com.develop_ping.union.notification.domain.dto.NotificationCommand;

public interface NotificationService {
    void createNotification(NotificationCommand command);
}

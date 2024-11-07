package com.develop_ping.union.notification.domain.service;

import com.develop_ping.union.notification.domain.dto.NotificationCommand;
import com.develop_ping.union.notification.domain.dto.NotificationInfo;

public interface NotificationService {
    NotificationInfo createNotification(NotificationCommand command);
}

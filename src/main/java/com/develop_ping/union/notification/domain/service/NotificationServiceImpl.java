package com.develop_ping.union.notification.domain.service;

import com.develop_ping.union.notification.domain.NotificationManager;
import com.develop_ping.union.notification.domain.dto.NotificationCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService{
    private final NotificationManager notificationManager;
    @Override
    public void createNotification(NotificationCommand command) {

    }
}

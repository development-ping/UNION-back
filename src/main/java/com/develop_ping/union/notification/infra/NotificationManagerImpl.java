package com.develop_ping.union.notification.infra;

import com.develop_ping.union.notification.domain.NotificationManager;
import com.develop_ping.union.notification.domain.entity.Notification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NotificationManagerImpl implements NotificationManager {
    private final NotificationRepository notificationRepository;

    public Notification save(Notification notification){
        return notificationRepository.save(notification);
    }

}

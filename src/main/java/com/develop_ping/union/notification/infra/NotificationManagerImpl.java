package com.develop_ping.union.notification.infra;

import com.develop_ping.union.notification.domain.NotificationManager;
import com.develop_ping.union.notification.domain.entity.Notification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class NotificationManagerImpl implements NotificationManager {
    private final NotificationRepository notificationRepository;

    public Notification save(Notification notification){
        return notificationRepository.save(notification);
    }

    @Override
    public List<Notification> findAllOrderByDate(String srcToken, Long page, Long size) {
        return notificationRepository.findAllOrderByDate(srcToken, page, size);
    }

}

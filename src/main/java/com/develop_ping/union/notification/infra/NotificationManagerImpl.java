package com.develop_ping.union.notification.infra;

import com.develop_ping.union.notification.domain.NotificationManager;
import com.develop_ping.union.notification.domain.entity.Notification;
import com.develop_ping.union.notification.infra.dto.NotificationReadForService;
import com.develop_ping.union.user.domain.entity.User;
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
    public List<NotificationReadForService> findAllOrderByDate(Long page, Long size, User user) {
        return notificationRepository.findAllOrderById(page, size, user);
    }

    @Override
    public void updateAll(Long page, Long size, User user) {
        notificationRepository.updateAll(page, size, user);
    }

}


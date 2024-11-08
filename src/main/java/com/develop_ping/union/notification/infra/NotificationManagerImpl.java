package com.develop_ping.union.notification.infra;

import com.develop_ping.union.notification.domain.NotificationManager;
import com.develop_ping.union.notification.domain.dto.NotificationInfo;
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
        System.out.println(111);
        return notificationRepository.findAllOrder(page, size, user);
    }

}


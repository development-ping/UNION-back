package com.develop_ping.union.notification.domain.service;

import com.develop_ping.union.notification.domain.NotificationManager;
import com.develop_ping.union.notification.domain.dto.NotificationCommand;
import com.develop_ping.union.notification.domain.dto.NotificationInfo;
import com.develop_ping.union.notification.domain.entity.Notification;
import com.develop_ping.union.post.domain.PostManager;
import com.develop_ping.union.user.domain.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService{
    private final NotificationManager notificationManager;
    private final PostManager postManager;
    @Override
    public NotificationInfo createNotification(NotificationCommand command) {
        String dstToken = postManager.findById(command.getTypeId()).getUser().getToken();

        Notification notification = notificationManager.save(Notification.of(command.getSrcToken(),
                dstToken, command.getType(), command.getTypeId(), false));

        return NotificationInfo.of(notification);
    }
}

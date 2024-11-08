package com.develop_ping.union.notification.infra;

import com.develop_ping.union.notification.domain.entity.Notification;
import com.develop_ping.union.notification.infra.dto.NotificationReadForService;
import com.develop_ping.union.user.domain.entity.User;

import java.util.List;

public interface NotificationRepositoryCustom {
    List<Notification> findAllOrderByDate(String srcToken, Long page, Long size);
    List<NotificationReadForService> findAllOrder(Long page, Long size, User user);
}

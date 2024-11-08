package com.develop_ping.union.notification.infra;

import com.develop_ping.union.notification.domain.entity.Notification;
import com.develop_ping.union.notification.infra.dto.NotificationReadForService;

import java.util.List;

public interface NotificationRepositoryCustom {
    List<Notification> findAllOrderByDate(String srcToken, Long page, Long size);
    List<NotificationReadForService> findAllOrder(String srcToken, Long page, Long size);
}

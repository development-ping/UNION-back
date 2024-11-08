package com.develop_ping.union.notification.domain;

import com.develop_ping.union.notification.domain.entity.Notification;

import java.util.List;

public interface NotificationManager {
    Notification save(Notification notification);
    List<Notification> findAllOrderByDate(String srcToken, Long page, Long size);
}

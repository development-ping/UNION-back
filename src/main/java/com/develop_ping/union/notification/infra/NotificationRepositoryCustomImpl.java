package com.develop_ping.union.notification.infra;

import com.develop_ping.union.notification.domain.entity.Notification;
import com.develop_ping.union.notification.domain.entity.QNotification;
import com.develop_ping.union.notification.infra.dto.NotificationReadForService;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

public class NotificationRepositoryCustomImpl implements NotificationRepositoryCustom{
    @PersistenceContext
    EntityManager em;
    private final QNotification qNotification = QNotification.notification;
    @Override
    public List<Notification> findAllOrderByDate(String srcToken, Long page, Long size) {
        return null;
    }

    @Override
    public List<NotificationReadForService> findAllOrder(String srcToken, Long page, Long size) {
        return null;
    }
}

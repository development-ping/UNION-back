package com.develop_ping.union.notification.infra;

import com.develop_ping.union.comment.domain.entity.QComment;
import com.develop_ping.union.gathering.domain.entity.QGathering;
import com.develop_ping.union.notification.domain.NotiType;
import com.develop_ping.union.notification.domain.entity.Notification;
import com.develop_ping.union.notification.domain.entity.QNotification;
import com.develop_ping.union.notification.infra.dto.NotificationReadForService;
import com.develop_ping.union.post.domain.entity.QPost;
import com.develop_ping.union.user.domain.entity.QUser;
import com.develop_ping.union.user.domain.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.sql.Timestamp;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

public class NotificationRepositoryCustomImpl implements NotificationRepositoryCustom{
    @PersistenceContext
    EntityManager em;
    @Override
    public List<Notification> findAllOrderByDate(String srcToken, Long page, Long size) {
        return null;
    }

    @Override
    public List<NotificationReadForService> findAllOrder(Long page, Long size, User user) {
        String query = """
                select *
                from(
                -- post
                select N.id, N.type, U.nickname, P.title, C.content, N.created_at, N.is_read
                from (select *
                    from notifications
                    where 1=1
                    and notifications.creator_id = :userId
                    order by notifications.id desc
                    limit :size
                    offset :offset) N
                join posts P on N.creator_type_id = P.id
                join comments C on N.attendee_type_id = C.id
                join users U on N.attendee_id = U.id
                where N.type = 'POST'
                
                union all
                
                -- comment
                select N.id, N.type, U.nickname, C1.content, C.content, N.created_at, N.is_read
                from (select *
                    from notifications
                    where 1=1
                    and notifications.creator_id = :userId
                    order by notifications.id desc
                    limit :size
                    offset :offset) N
                join comments C1 on N.creator_type_id = C1.id
                join comments C on N.attendee_type_id = C.id
                join users U on N.attendee_id = U.id
                where N.type = 'COMMENT'
                
                union all
                
                -- gathering
                select N.id, N.type, U.nickname, G.title, 0, N.created_at, N.is_read
                from (select *
                    from notifications
                    where 1=1
                    and notifications.creator_id = :userId
                    order by notifications.id desc
                    limit :size
                    offset :offset) N
                join gatherings G on N.creator_type_id = G.id
                join users U on N.attendee_id = U.id
                where N.type = 'GATHERING'
                ) tb
                order by tb.id desc;
                """;
        List<Object[]> results = em.createNativeQuery(query)
                .setParameter("userId", user.getId())
                .setParameter("size", size)
                .setParameter("offset", page * size)
                .getResultList();

        List<NotificationReadForService> notificationList = new ArrayList<>();

        for (Object[] result : results) {
            Long id = ((Number) result[0]).longValue();
            String stringType = (String) result[1];
            NotiType type = NotiType.valueOf(stringType);
            String nickname = (String) result[2];
            String title = (String) result[3];
            String content = (String) result[4];

            // change date
            Timestamp timestamp = (Timestamp) result[5];
            ZonedDateTime createdAt = timestamp.toInstant().atZone(ZoneId.systemDefault());

            Boolean isRead = (Boolean) result[6];

            NotificationReadForService notification = new NotificationReadForService(id, type, nickname, title, content, createdAt, isRead);
            notificationList.add(notification);
        }

        return notificationList;
    }


}
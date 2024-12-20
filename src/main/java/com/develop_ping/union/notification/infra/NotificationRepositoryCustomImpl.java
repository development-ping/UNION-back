package com.develop_ping.union.notification.infra;

import com.develop_ping.union.notification.domain.NotiType;
import com.develop_ping.union.notification.domain.entity.Notification;
import com.develop_ping.union.notification.infra.dto.NotificationReadForService;
import com.develop_ping.union.post.domain.entity.PostType;
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
    public List<NotificationReadForService> findAllOrderById(Long page, Long size, User user) {
        String queryForRead = """
                select *
                from(
                -- post
                select N.id, N.type, U.nickname, P.title, C.content, N.created_at, N.is_read, P.id as post_id, P.type as post_type1
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
                select N.id, N.type, U.nickname, C1.content, C.content, N.created_at, N.is_read, C1.id as comment_id, P.type as post_type1
                from (select *
                    from notifications
                    where 1=1
                    and notifications.creator_id = :userId
                    order by notifications.id desc
                    limit :size
                    offset :offset) N
                join comments C1 on N.creator_type_id = C1.id
                join posts P on P.id = C1.post_id
                join comments C on N.attendee_type_id = C.id
                join users U on N.attendee_id = U.id
                where N.type = 'COMMENT'
                
                union all
                
                -- gathering
                select N.id, N.type, U.nickname, G.title, 0, N.created_at, N.is_read, G.id as gathering_id, 'FREE'
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
        List<Object[]> results = em.createNativeQuery(queryForRead)
                .setParameter("userId", user.getId())
                .setParameter("size", size)
                .setParameter("offset", page * size)
                .getResultList();

        List<NotificationReadForService> notificationList = new ArrayList<>();

        for (Object[] result : results) {
            Long id = ((Number) result[0]).longValue();
            NotiType type = NotiType.valueOf((String) result[1]);
            String nickname = (String) result[2];
            String title = (String) result[3];
            String content = (String) result[4];

            // change date
            Timestamp timestamp = (Timestamp) result[5];
            ZonedDateTime createdAt = timestamp.toInstant().atZone(ZoneId.systemDefault());

            Boolean isRead = (Boolean) result[6];
            Long typeId = ((Number) result[7]).longValue();

            PostType postType = PostType.valueOf((String) result[8]);

            NotificationReadForService notification = new NotificationReadForService(id, type, typeId, nickname, title, content, createdAt, isRead, postType);
            notificationList.add(notification);
        }

        return notificationList;
    }

    @Override
    public Notification updateIsRead(Long id, User user) {
        // update query
        String queryForUpdate = """
                UPDATE notifications
                SET is_read = true
                WHERE 1=1
                AND id = :id;
                """;

        em.createNativeQuery(queryForUpdate)
                .setParameter("id", id)
                .executeUpdate();

        return em.find(Notification.class, id);
    }


}
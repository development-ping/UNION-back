package com.develop_ping.union.notification.presentation.dto.response;

import com.develop_ping.union.notification.domain.NotiType;
import com.develop_ping.union.notification.domain.dto.NotificationInfo;
import com.develop_ping.union.notification.domain.entity.Notification;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotificationReadForResponses {
    private List<NotificationReadForResponse> notifications;

    static public NotificationReadForResponses from(NotificationInfo info) {
        List<NotificationReadForResponse> notifications = new ArrayList<>();

//        for (Notification notification : info.getNotifications()) {
//            notifications.add(NotificationReadForResponse.builder()
//                    .id(notification.getId())
//                    .type(notification.getType())
//                    .nickname()
//                    .title()
//                    .content()
//                    .createdAt()
//                    .isRead()
//                    .build());
//        }

        return NotificationReadForResponses.builder()
                .notifications(notifications)
                .build();
    }
}

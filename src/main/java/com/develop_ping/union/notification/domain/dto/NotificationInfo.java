package com.develop_ping.union.notification.domain.dto;

import com.develop_ping.union.notification.domain.NotiType;
import com.develop_ping.union.notification.domain.entity.Notification;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class NotificationInfo {
    private Long id;
    private String srcToken;
    private String dstToken;
    private NotiType type;
    private Long typeId;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;
    private Boolean isRead;

    public static NotificationInfo of(Notification notification){
        return NotificationInfo.builder()
                .id(notification.getId())
                .srcToken(notification.getSrcToken())
                .dstToken(notification.getDstToken())
                .type(notification.getType())
                .typeId(notification.getTypeId())
                .createdAt(ZonedDateTime.now())
                .updatedAt(ZonedDateTime.now())
                .isRead(notification.getIsRead())
                .build();
    }
}

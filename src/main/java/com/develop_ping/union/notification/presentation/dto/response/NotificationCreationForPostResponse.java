package com.develop_ping.union.notification.presentation.dto.response;

import com.develop_ping.union.notification.domain.NotiType;
import com.develop_ping.union.notification.domain.dto.NotificationInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotificationCreationForPostResponse {
    private Long id;
    private String srcToken;
    private String dstToken;
    private NotiType type;
    private Long typeId;
    private ZonedDateTime createdAt;
    private Boolean isRead;
    public static NotificationCreationForPostResponse from(NotificationInfo info){
        return NotificationCreationForPostResponse.builder()
                .id(info.getId())
                .srcToken(info.getSrcToken())
                .dstToken(info.getDstToken())
                .type(info.getType())
                .typeId(info.getTypeId())
                .createdAt(info.getCreatedAt())
                .isRead(info.getIsRead())
                .build();
    }
}

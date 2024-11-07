package com.develop_ping.union.notification.presentation.dto.response;

import com.develop_ping.union.notification.domain.NotiType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotificationCreationForCommentResponse {
    private Long id;
    private Long userId;
    private NotiType type;
    private Long typeId;
    private ZonedDateTime createdAt;
    private Boolean isRead;
}

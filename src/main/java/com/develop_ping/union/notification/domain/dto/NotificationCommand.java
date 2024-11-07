package com.develop_ping.union.notification.domain.dto;

import com.develop_ping.union.notification.domain.NotiType;
import com.develop_ping.union.notification.presentation.dto.request.NotificationCreationForPostRequest;
import com.develop_ping.union.user.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class NotificationCommand {
    private NotiType type;
    private Long typeId;
    private String srcToken;

}

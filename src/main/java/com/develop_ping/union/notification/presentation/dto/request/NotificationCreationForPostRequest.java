package com.develop_ping.union.notification.presentation.dto.request;

import com.develop_ping.union.notification.domain.NotiType;
import com.develop_ping.union.notification.domain.dto.NotificationCommand;
import com.develop_ping.union.user.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotificationCreationForPostRequest {
    private Long typeId;

    public NotificationCommand postToCommand(User user) {
        return NotificationCommand.builder()
                .type(NotiType.POST)
                .typeId(this.typeId)
                .srcToken(user.getToken())
                .build();
    }
}

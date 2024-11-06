package com.develop_ping.union.notification.presentation.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotificationCreationForPostRequest {
    String userToken;
    Long typeId;
}

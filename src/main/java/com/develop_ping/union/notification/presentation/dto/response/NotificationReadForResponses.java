package com.develop_ping.union.notification.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotificationReadForResponses {
    private List<NotificationCreationForCommentResponse> notifications;
}

package com.develop_ping.union.notification.presentation.dto.request;

import com.develop_ping.union.notification.domain.NotiType;
import com.develop_ping.union.notification.domain.dto.NotificationCommand;
import com.develop_ping.union.user.domain.entity.User;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class NotificationCreationForCommentRequest {
    @NotNull
    private Long typeId;

    @NotNull
    private Long commentId;

    public NotificationCommand toCommand(User user) {
        return NotificationCommand.builder()
                .type(NotiType.COMMENT)
                .typeId(this.typeId)
                .commentId(this.commentId)
                .build();
    }
}

package com.develop_ping.union.notification.presentation;

import com.develop_ping.union.notification.domain.service.NotificationService;
import com.develop_ping.union.notification.presentation.dto.request.NotificationCreationForCommentRequest;
import com.develop_ping.union.notification.presentation.dto.request.NotificationCreationForGatheringRequest;
import com.develop_ping.union.notification.presentation.dto.request.NotificationCreationForPostRequest;
import com.develop_ping.union.user.domain.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/notification")
@Slf4j
@RequiredArgsConstructor
public class NotificationController {
    private final NotificationService notificationService;

    @PostMapping("/post")
    public ResponseEntity<HttpStatus> createNotificationForPost(@RequestBody NotificationCreationForPostRequest request,
                                                                @AuthenticationPrincipal User user){
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/comment")
    public ResponseEntity<HttpStatus> createNotificationForComment(@RequestBody NotificationCreationForCommentRequest request,
                                                                   @AuthenticationPrincipal User user){
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/gathering")
    public ResponseEntity<HttpStatus> createNotificationForGathering(@RequestBody NotificationCreationForGatheringRequest request,
                                                                     @AuthenticationPrincipal User user){
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}

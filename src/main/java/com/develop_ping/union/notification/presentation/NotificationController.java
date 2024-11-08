package com.develop_ping.union.notification.presentation;

import com.develop_ping.union.notification.domain.dto.NotificationCommand;
import com.develop_ping.union.notification.domain.dto.NotificationInfo;
import com.develop_ping.union.notification.domain.service.NotificationService;
import com.develop_ping.union.notification.presentation.dto.request.NotificationCreationForCommentRequest;
import com.develop_ping.union.notification.presentation.dto.request.NotificationCreationForGatheringRequest;
import com.develop_ping.union.notification.presentation.dto.request.NotificationCreationForPostRequest;
import com.develop_ping.union.notification.presentation.dto.response.*;
import com.develop_ping.union.user.domain.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.ResourceBundle;

@RestController
@RequestMapping("/notification")
@Slf4j
@RequiredArgsConstructor
public class NotificationController {
    private final NotificationService notificationService;

    @PostMapping("/post")
    public ResponseEntity<NotificationCreationForPostResponse> createNotificationForPost(@RequestBody NotificationCreationForPostRequest request, @AuthenticationPrincipal User user){
        NotificationCommand command = request.toCommand(user);
        NotificationInfo info = notificationService.createNotificationForPost(command);
        NotificationCreationForPostResponse response = NotificationCreationForPostResponse.from(info);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/comment")
    public ResponseEntity<NotificationCreationForCommentResponse> createNotificationForComment(@RequestBody NotificationCreationForCommentRequest request, @AuthenticationPrincipal User user){
        NotificationCommand command = request.toCommand(user);
        NotificationInfo info = notificationService.createNotificationForComment(command);
        NotificationCreationForCommentResponse response = NotificationCreationForCommentResponse.from(info);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/gathering")
    public ResponseEntity<NotificationCreationForGatheringResponse> createNotificationForGathering(@RequestBody NotificationCreationForGatheringRequest request, @AuthenticationPrincipal User user){
        NotificationCommand command = request.toCommand(user);
        NotificationInfo info = notificationService.createNotificationForGathering(command);
        NotificationCreationForGatheringResponse response = NotificationCreationForGatheringResponse.from(info);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @GetMapping("/")
    public ResponseEntity<NotificationReadForResponses> readNotification(@RequestParam("page") Long page, @RequestParam("size") Long size, @AuthenticationPrincipal User user){

        return null;
    }
    @PostMapping("/read")
    public ResponseEntity<HttpStatus> createNotificationIsRead(@RequestParam("page") Long page, @RequestParam("size") Long size){

        return ResponseEntity.status(HttpStatus.OK).build();
    }
}

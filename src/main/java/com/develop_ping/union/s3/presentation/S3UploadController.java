package com.develop_ping.union.s3.presentation;

import com.develop_ping.union.s3.infra.S3Manager;
import com.develop_ping.union.user.domain.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class S3UploadController {
    private final S3Manager s3Manager;

    @PostMapping("/photo/upload")
    public ResponseEntity<List<String>> upload(@RequestParam("images") MultipartFile[] images,
                                               @AuthenticationPrincipal User user) {
        log.info("[ CALL: S3UploadController.upload() ]");

        return ResponseEntity.ok(s3Manager.uploadImages(images, user));
    }
}

package com.develop_ping.union.photo.domain.service;

import com.develop_ping.union.photo.domain.PhotoManager;
import com.develop_ping.union.photo.domain.dto.PhotoCommand;
import com.develop_ping.union.photo.domain.entity.Photo;
import com.develop_ping.union.s3.infra.S3Manager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PhotoServiceImpl implements PhotoService {
    private final PhotoManager photoManager;
    private final S3Manager s3Manager;

    @Override
    public void savePhotos(PhotoCommand command) {
        log.info("[ CALL: PhotoService.savePhoto() ] ");

        // TODO: command.getToken()으로 user id 뽑기
        Long userId = 1L;

        // target 소유 여부 확인 -> targetType이 나중에 새로 추가되는게 있다면 어떻게 해야할까?
        switch (command.getTargetType()) {
            case POST -> validatePostOwner(userId, command.getTargetId());
            case GATHERING -> validateGatheringOwner(userId, command.getTargetId());
        }

        // 이미지 url이 s3에 존재하는지 확인
        List<String> urls = command.getUrls();
        urls.forEach(s3Manager::validateObjectExists);

        List<Photo> photos = photoManager.savePhotos(command.getTargetId(), command.getTargetType(), urls);

        photos.forEach(photo -> log.info(photo.toString()));
    }

    private void validateGatheringOwner(Long userId, Long gatheringId) {
        // TODO: userId로 gatheringId의 owner인지 확인
    }

    private void validatePostOwner(Long userId, Long postId) {
        // TODO: userId로 postId의 owner인지 확인
    }
}
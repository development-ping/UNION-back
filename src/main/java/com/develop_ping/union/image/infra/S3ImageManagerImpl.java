package com.develop_ping.union.image.infra;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.develop_ping.union.image.exception.ImageUploadFailedException;
import com.develop_ping.union.image.exception.UnsupportedFileFormatException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class S3ImageManagerImpl implements S3ImageManager {
    private static final List<String> SUPPORTED_EXTENSIONS = Arrays.asList(
            ".jpg", ".jpeg", ".png", ".gif", ".webp", ".svg"
    );

    private final AmazonS3Client amazonS3Client;

    @Value("${cloud.aws.s3.bucket}")
    private String bucketName;

    @Override
    public List<String> uploadImages(MultipartFile[] images, String token) {
        log.info("uploading images with token: {}", token);

        // TODO: token에서 user token 추출
        String userToken = token;

        // 리스트 반환
        return Arrays.stream(images)
                .map(image -> {
                    try {
                        return uploadSingleImage(image, userToken);
                    } catch (IOException e) {
                        throw new ImageUploadFailedException(image.getOriginalFilename());
                    }
                })
                .collect(Collectors.toList());
    }

    // 이미지 업로드
    private String uploadSingleImage(MultipartFile image, String userToken) throws IOException {
        String filePath = generateFilePath(image.getOriginalFilename(), userToken);
        ObjectMetadata metadata = createObjectMetadata(image);

        amazonS3Client.putObject(bucketName, filePath, image.getInputStream(), metadata);

        return amazonS3Client.getUrl(bucketName, filePath).toString();
    }

    // 파일 경로 생성
    private String generateFilePath(String originalFileName, String userToken) {
        String fileName = generateFileName(originalFileName);
        return userToken + "/" + fileName;
    }

    // 파일 메타데이터 생성
    private ObjectMetadata createObjectMetadata(MultipartFile image) {
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(image.getSize());
        metadata.setContentType(image.getContentType());
        return metadata;
    }

    // 파일 이름 생성
    private String generateFileName(String originalFileName) {
        String extension = extractExtension(originalFileName);

        // 확장자 검증
        if (!isSupportedExtension(extension)) {
            throw new UnsupportedFileFormatException(extension);
        }

        return UUID.randomUUID().toString() + extension;
    }

    // 파일 확장자 추출
    private String extractExtension(String originalFileName) {
        try {
            return originalFileName.substring(originalFileName.lastIndexOf(".")).toLowerCase();  // 확장자를 소문자로 변환
        } catch (StringIndexOutOfBoundsException e) {
            // 확장자가 없는 경우 에러 발생
            throw new UnsupportedFileFormatException("null");
        }
    }

    private boolean isSupportedExtension(String extension) {
        return SUPPORTED_EXTENSIONS.contains(extension);
    }
}

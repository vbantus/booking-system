package com.fablab.booking.bootstrap;

import com.fablab.booking.service.MinioService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BucketInitializer implements CommandLineRunner {

    @Value("${minio.buckek.article.name}")
    private String articleBucket;

    @Value("${minio.buckek.event.name}")
    private String eventBucket;

    @Value("${minio.buckek.room.name}")
    private String roomBucket;

    private final MinioService minioService;

    @Override
    public void run(String... args) {

        if (!minioService.bucketExist(articleBucket)) {
            createBucketWithReadOnlyPolicy(articleBucket);
        }

        if (!minioService.bucketExist(eventBucket)) {
            createBucketWithReadOnlyPolicy(eventBucket);
        }

        if (!minioService.bucketExist(roomBucket)) {
            createBucketWithReadOnlyPolicy(roomBucket);
        }
    }

    private void createBucketWithReadOnlyPolicy(String bucketName) {
        minioService.createBucket(bucketName);
        minioService.setBucketPolicyToReadOnly(bucketName);
    }
}

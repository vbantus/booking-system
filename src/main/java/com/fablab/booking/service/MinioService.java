package com.fablab.booking.service;

import org.springframework.web.multipart.MultipartFile;

public interface MinioService {

    void createBucket(String bucketName);

    boolean bucketExist(String bucketName);

    void setBucketPolicyToReadOnly(String bucketName);

    String saveImage(MultipartFile multipartFile, String bucketName);
}

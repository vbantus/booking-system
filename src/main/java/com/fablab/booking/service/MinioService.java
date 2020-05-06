package com.fablab.booking.service;

public interface MinioService {

    void createBucket(String bucketName);

    boolean bucketExist(String bucketName);

    void setBucketPolicyToReadOnly(String bucketName);

    String uploadImage(String name, byte[] content, String bucketName);
}

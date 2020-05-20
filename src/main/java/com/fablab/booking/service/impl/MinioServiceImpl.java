package com.fablab.booking.service.impl;

import com.fablab.booking.service.MinioService;
import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.UUID;

@Service
public class MinioServiceImpl implements MinioService {

    private static final String DEFAULT_IMAGE_URL = "https://images.unsplash.com/photo-1535982330050-f1c2fb79ff78?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&w=1000&q=80";

    @Autowired
    private MinioClient minioClient;

    @Value("${minio.image.url}")
    private String minioImageUrl;

    @Override
    public void createBucket(String bucketName) {
        try {
            minioClient.makeBucket(bucketName);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public boolean bucketExist(String bucketName) {
        try {
            return minioClient.bucketExists(bucketName);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void setBucketPolicyToReadOnly(String bucketName) {
        String readOnlyPolicy = "{\n" +
                "    \"Statement\": [\n" +
                "        {\n" +
                "            \"Action\": [\n" +
                "                \"s3:GetBucketLocation\",\n" +
                "                \"s3:ListBucket\"\n" +
                "            ],\n" +
                "            \"Effect\": \"Allow\",\n" +
                "            \"Principal\": \"*\",\n" +
                "            \"Resource\": \"arn:aws:s3:::%s\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Action\": \"s3:GetObject\",\n" +
                "            \"Effect\": \"Allow\",\n" +
                "            \"Principal\": \"*\",\n" +
                "            \"Resource\": \"arn:aws:s3:::%s/*\"\n" +
                "        }\n" +
                "    ],\n" +
                "    \"Version\": \"2012-10-17\"\n" +
                "}";

        try {
            minioClient.setBucketPolicy(bucketName, String.format(readOnlyPolicy, bucketName, bucketName));
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public String saveImage(MultipartFile multipartFile, String bucketName) {

        if (multipartFile != null) {
            String imageName = "image_" + UUID.randomUUID().toString() + ".jpg";
            try {
                return uploadImage(imageName, multipartFile.getBytes(), bucketName);
            } catch (IOException e) {
                throw new RuntimeException(e.getMessage());
            }
        }

        return DEFAULT_IMAGE_URL;
    }

    private String uploadImage(String name, byte[] content, String bucketName) {
        try {
            minioClient.putObject(bucketName, name, new ByteArrayInputStream(content), content.length, "image/jpeg");
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

        return minioImageUrl + "/" + bucketName + "/" + name;
    }

}

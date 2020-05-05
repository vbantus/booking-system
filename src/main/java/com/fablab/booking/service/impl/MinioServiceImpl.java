package com.fablab.booking.service.impl;

import com.fablab.booking.service.MinioService;
import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;

@Service
public class MinioServiceImpl implements MinioService {

    @Autowired
    private MinioClient minioClient;

    @Value("${minio.url}")
    private String minioUrl;

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
    public String uploadImage(String name, byte[] content, String bucketname) {
        try {
            minioClient.putObject(bucketname, name, new ByteArrayInputStream(content), content.length, "image/jpeg");
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

        return minioUrl + "/" + bucketname + "/" + name;
    }

}

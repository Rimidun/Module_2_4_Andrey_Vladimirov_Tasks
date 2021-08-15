package project.service;


import software.amazon.awssdk.services.s3.model.S3Object;

import java.util.List;

public interface S3Service {

    S3Object upload(String path, String bucket);
    List<S3Object> getFileList(String bucket);
    S3Object get(String fileName, String bucket);
    void remove(String fileName, String bucket);
    void download(String bucket, String fileName, String destination);
}
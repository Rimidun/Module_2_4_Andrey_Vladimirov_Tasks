package project.service.implementation;


import project.repository.S3Repository;
import project.repository.implementation.S3RepositoryImplementation;
import project.service.S3Service;
import software.amazon.awssdk.services.s3.model.S3Object;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;


public class S3ServiceImplementation implements S3Service {
    private final S3Repository s3Repository;

    public S3ServiceImplementation() {
        this.s3Repository = new S3RepositoryImplementation();
    }

    @Override
    public S3Object upload(String path, String bucket) {
        if(Files.exists(Paths.get(path))){
            return s3Repository.upload(path, bucket);
        }

        return null;
    }

    @Override
    public List<S3Object> getFileList(String bucket) {
        return s3Repository.getFileList(bucket);
    }

    @Override
    public S3Object get(String fileName, String bucket) {
        return s3Repository.get(fileName, bucket);
    }

    @Override
    public void remove(String fileName, String bucket) {
        s3Repository.remove(fileName, bucket);
    }

    @Override
    public void download(String bucket, String fileName, String destination) {
        s3Repository.writeObjectToPath(bucket, fileName, destination);
    }
}

package project.repository.implementation;

import project.repository.S3Repository;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class S3RepositoryImplementation implements S3Repository {
    private final S3Client s3Client;

    public S3RepositoryImplementation() {
        this.s3Client = S3Client.builder()
                .region(Region.EU_WEST_3)
                .build();
    }

    public S3Object upload(String path, String bucket) {
        File file = new File(path);
        PutObjectRequest por = PutObjectRequest.builder()
                .bucket(bucket)
                .key(file.getName())
                .build();
        s3Client.putObject(por, file.toPath());

        return get(bucket, file.getName());
    }

    @Override
    public List<S3Object> getFileList(String bucket) {
        ListObjectsRequest request = ListObjectsRequest.builder()
                .bucket(bucket)
                .build();
        ListObjectsResponse resp = s3Client.listObjects(request);
        return resp.contents();
    }

    @Override
    public S3Object get(String bucket, String fileName) {
        return getFileList(bucket).stream()
                .filter(o -> o.key().equals(fileName))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void remove(String fileName, String bucket) {
        DeleteObjectRequest request = DeleteObjectRequest.builder()
                .bucket(bucket)
                .key(fileName)
                .build();
        s3Client.deleteObject(request);
    }

    @Override
    public void writeObjectToPath(String bucket, String fileName, String destination){
        Path path = Paths.get(destination + "/" + fileName);
        if(!Files.exists(path)){
            try {
                Files.createFile(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            Files.write(path, getObjectBytes(fileName, bucket), StandardOpenOption.WRITE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public byte[] getObjectBytes(String fileName, String bucket){
        GetObjectRequest request = GetObjectRequest.builder()
                .bucket(bucket)
                .key(fileName)
                .build();
        return s3Client.getObjectAsBytes(request).asByteArray();
    }
}

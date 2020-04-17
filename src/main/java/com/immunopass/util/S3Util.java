package com.immunopass.util;

import static java.util.concurrent.CompletableFuture.supplyAsync;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;


@Service
public class S3Util {

    private final AmazonS3 s3Client;
    private final String bucket;

    public S3Util(
            @Value("${aws.s3.accessKeyId}") String accessKeyId,
            @Value("${aws.s3.secretAccessKey}") String secretAccessKey,
            @Value("${aws.s3.region}") String region,
            @Value("${aws.s3.bucket}") String bucket) {
        AWSStaticCredentialsProvider credentials =
                new AWSStaticCredentialsProvider(new BasicAWSCredentials(accessKeyId, secretAccessKey));
        this.s3Client = AmazonS3ClientBuilder.standard().withCredentials(credentials).withRegion(region).build();
        this.bucket = bucket;
    }

    public URL uploadDocumentSync(InputStream inputStream, String contentType, String key) {
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(contentType);
        return supplyAsync(
                () -> {
                    s3Client.putObject(bucket, key, inputStream, metadata);
                    return s3Client.getUrl(this.bucket, key);
                })
                .toCompletableFuture()
                .join();
    }

    public Stream<String> getRecords(String key) {
        String[] keyParts = key.split("/");
        key = keyParts[keyParts.length - 1];
        GetObjectRequest getObjectRequest = new GetObjectRequest(bucket, key);
        InputStream orderFileStream =
                supplyAsync(() ->
                        s3Client
                                .getObject(getObjectRequest)
                                .getObjectContent())
                        .toCompletableFuture()
                        .join();
        return new BufferedReader(new InputStreamReader(orderFileStream)).lines();
    }
}

package com.immunopass.util;

import static java.util.concurrent.CompletableFuture.supplyAsync;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletionStage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;

@Service
public class S3Util {
    public static final Long FIFTEEN_MINUTES_IN_MILLIS = Long.valueOf(900000L);
    protected final AWSStaticCredentialsProvider credentials;
    private final AmazonS3 s3Client;
    private final String bucket;

    public S3Util(
            @Value("${aws.s3.accessKeyId}") String accessKeyId,
            @Value("${aws.s3.secretAccessKey}") String secretAccessKey,
            @Value("${aws.s3.region}") String region,
            @Value("${aws.s3.bucket}") String bucket) {
        this.credentials =
                new AWSStaticCredentialsProvider(new BasicAWSCredentials(accessKeyId, secretAccessKey));
        this.bucket = bucket;
        this.s3Client =
                AmazonS3ClientBuilder.standard().withCredentials(credentials).withRegion(region).build();
    }

    public URL uploadAndGetSignedURLSync(
            InputStream inputStream, String contentType, Date expiryDate, String key) {
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(contentType);
        return supplyAsync(
                () -> {
                    s3Client.putObject(bucket, key, inputStream, metadata);
                    GeneratePresignedUrlRequest request = new GeneratePresignedUrlRequest(bucket, key);
                    request.setExpiration(expiryDate);
                    return s3Client.generatePresignedUrl(request);
                })
                .toCompletableFuture()
                .join();
    }

    public URL uploadDocumentSync(
            InputStream inputStream, String contentType, Date expiryDate, String key) {
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

    public CompletionStage<URL> getSignedURL(String key) {
        return supplyAsync(
                () -> {
                    GeneratePresignedUrlRequest request = new GeneratePresignedUrlRequest(bucket, key);
                    request.setExpiration(new Date(System.currentTimeMillis() + FIFTEEN_MINUTES_IN_MILLIS));
                    return s3Client.generatePresignedUrl(request);
                });
    }

    public CompletionStage<InputStream> getFileStream(String key) {
        return getFileStream(bucket, key);
    }

    public List<String> getFileLinesWithoutHeader(String key) throws IOException {
        String[] keyParts = key.split("/");
        key = keyParts[keyParts.length-1];
        InputStream orderFileStream = getFileStream(key).toCompletableFuture().join();
        BufferedReader br = new BufferedReader(new InputStreamReader(orderFileStream));
        String line;
        List<String> lines = new ArrayList<>();
        while ((line = br.readLine()) != null) {
            lines.add(line);
        }
        return lines.subList(1, lines.size());
    }

    public CompletionStage<InputStream> getFileStream(String bucket, String key) {
        try {
            GetObjectRequest getObjectRequest = new GetObjectRequest(bucket, key);
            return supplyAsync(() -> s3Client.getObject(getObjectRequest).getObjectContent());

        } catch (Exception e) {
            throw new RuntimeException(
                    "Unable to fetch file from s3 reason: " + e.getLocalizedMessage());
        }
    }
}

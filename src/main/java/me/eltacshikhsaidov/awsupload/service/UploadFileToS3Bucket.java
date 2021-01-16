package me.eltacshikhsaidov.awsupload.service;

import com.amazonaws.services.s3.AmazonS3;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;

@Service
public class UploadFileToS3Bucket {

    private final AmazonS3 amazonS3;

    public UploadFileToS3Bucket(AmazonS3 amazonS3) {
        this.amazonS3 = amazonS3;
    }

    @Value("${application.bucket.name}")
    private String bucketName;

    public String uploadFile(MultipartFile multipartFile){
        File file =  multipartFileToFile(multipartFile);

        String fileName = "FileName is " + multipartFile.getOriginalFilename();

        amazonS3.putObject(bucketName,fileName,file);
        file.delete();

        return fileName;

    }

    private File multipartFileToFile(MultipartFile multipartFile) {
        File file = new File(Objects.requireNonNull(multipartFile.getOriginalFilename()));

        try(FileOutputStream fileOutputStream = new FileOutputStream(file)){
            fileOutputStream.write(multipartFile.getBytes());
        }
        catch (IOException exception){
            System.out.println(exception.getMessage());
        }

        return file;
    }

}

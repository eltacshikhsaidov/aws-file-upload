package me.eltacshikhsaidov.awsupload.controller;

import me.eltacshikhsaidov.awsupload.service.UploadFileToS3Bucket;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class UploadFileController {

    private final UploadFileToS3Bucket uploadFileToS3Bucket;

    public UploadFileController(UploadFileToS3Bucket uploadFileToS3Bucket) {
        this.uploadFileToS3Bucket = uploadFileToS3Bucket;
    }

    @GetMapping("/")
    public String index() {
        return "upload";
    }

    @PostMapping("/file")
    public String uploadFile(@RequestParam(value = "file")MultipartFile file, Model model) {
        String fileName = uploadFileToS3Bucket.uploadFile(file);
        model.addAttribute("fileName", fileName);

        return "upload";
    }
}

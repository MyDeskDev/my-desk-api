package com.mydesk.api.common.controller;

import com.mydesk.api.common.service.AwsS3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@CrossOrigin
public class AwsController {

    private final AwsS3Service awsS3Service;

    @PostMapping("/upload")
    public String uploadImg(@RequestPart(value = "file") MultipartFile file) {
        return awsS3Service.uploadImg(file);
    }
}

package com.mydesk.api.common.controller;

import com.mydesk.api.post.dto.PostCreateRequestByAdminDto;
import com.mydesk.api.post.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private PostService postService;

    @GetMapping("/api/v1/test")
    private String userTest() {
        return "test1";
    }
}

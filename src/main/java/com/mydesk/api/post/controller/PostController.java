package com.mydesk.api.post.controller;

import com.mydesk.api.config.auth.LoginUser;
import com.mydesk.api.config.auth.dto.SessionUser;
import com.mydesk.api.post.dto.PostCreateRequestByAdminDto;
import com.mydesk.api.post.dto.PostCreateRequestDto;
import com.mydesk.api.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class PostController {

    private final PostService postService;

    @PostMapping("/api/v1/posts")
    private Long createPost(@LoginUser SessionUser userDto, @RequestBody PostCreateRequestDto requestDto) {
        Long postId = postService.create(userDto, requestDto);
        return postId;
    }

    @PostMapping("/api/v1/manage/posts")
    private Long createPostByAdmin(@RequestBody PostCreateRequestByAdminDto requestDto) {
        Long postId = postService.createByAdmin(requestDto);
        return postId;
    }

//    @PostMapping("/test")
//    private Long createPostTest(@RequestBody PostCreateRequestByAdminDto requestDto) {
//        Long postId = postService.createByAdmin(requestDto);
//        return postId;
//    }
}

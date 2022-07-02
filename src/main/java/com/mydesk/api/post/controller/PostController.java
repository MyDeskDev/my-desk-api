package com.mydesk.api.post.controller;

import com.mydesk.api.config.auth.LoginUser;
import com.mydesk.api.config.auth.dto.SessionUser;
import com.mydesk.api.post.dto.PostCreateRequestByAdminDto;
import com.mydesk.api.post.dto.PostCreateRequestDto;
import com.mydesk.api.post.dto.PostListResponseDto;
import com.mydesk.api.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class PostController {

    private final PostService postService;

    @GetMapping("/api/v1/post")
    public List<PostListResponseDto> getPostList() {
//        TODO: paging, searching
        return postService.getPostList();
    }

    @PostMapping("/api/v1/post")
    public Long createPost(@LoginUser SessionUser userDto, @RequestBody PostCreateRequestDto requestDto) {
        return postService.create(userDto, requestDto);
    }

    @PostMapping("/api/v1/manage/post")
    public Long createPostByAdmin(@RequestBody PostCreateRequestByAdminDto requestDto) {
        return postService.createByAdmin(requestDto);
    }
}

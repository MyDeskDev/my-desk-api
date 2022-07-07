package com.mydesk.api.post.controller;

import com.mydesk.api.config.auth.LoginUser;
import com.mydesk.api.config.auth.dto.SessionUser;
import com.mydesk.api.post.dto.*;
import com.mydesk.api.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
    public Long create(@LoginUser SessionUser userDto, @RequestBody PostCreateRequestDto requestDto) {
        return postService.create(userDto, requestDto);
    }

    @GetMapping("/api/v1/post/{id}")
    public PostResponseDto getPost(@PathVariable Long id) {
        return postService.getPost(id);
    }

    @PutMapping("/api/v1/post/{id}")
    public PostResponseDto update(@LoginUser SessionUser userDto, @PathVariable Long id, @RequestBody PostUpdateRequestDto requestDto) throws Exception {
        return postService.update(userDto, id, requestDto);
    }

    @PostMapping("/api/v1/manage/post")
    public Long createByAdmin(@RequestBody PostCreateRequestByAdminDto requestDto) {
        return postService.createByAdmin(requestDto);
    }
}

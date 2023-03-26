package com.mydesk.api.post.controller;

import com.mydesk.api.generic.PageRequest;
import com.mydesk.api.post.dto.*;
import com.mydesk.api.post.service.PostService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@Api
@RequiredArgsConstructor
@RestController
public class PostController {

    private final PostService postService;

    @GetMapping("/api/v1/posts")
    public List<PostListResponseDto> getPosts(PageRequest pageRequest) {
        return postService.getPostList(pageRequest);
    }

    @ApiOperation(value="포스트 생성")
    @ApiResponses({
          @ApiResponse(code = 201, message = "성공"),
          @ApiResponse(code = 403, message = "권한 없음")
    })
    @PutMapping("/api/v1/posts")
    public Long create(@Valid @RequestBody PostCreateRequestDto requestDto) {
        return postService.create(requestDto);
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostResponseDto getPost(@PathVariable Long id) throws IllegalArgumentException {
        return postService.getPost(id);
    }

    @ApiOperation(value="포스트 임시저장")
    @ApiResponses({
            @ApiResponse(code = 201, message = "성공"),
            @ApiResponse(code = 403, message = "권한 없음")
    })
    @PutMapping("/api/v1/posts/temp-save")
    public UUID tempSave(@Valid @RequestBody PostTempSaveDto requestDto) {
        return postService.tempSave(requestDto);
    }


//    @PostMapping("/api/v1/manage/posts")
//    public Long createByAdmin(@Valid @RequestBody PostCreateRequestByAdminDto requestDto) {
//        return postService.createByAdmin(requestDto);
//    }
}

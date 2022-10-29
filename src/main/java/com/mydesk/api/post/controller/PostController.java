package com.mydesk.api.post.controller;

import com.mydesk.api.config.auth.LoginUser;
import com.mydesk.api.config.auth.dto.SessionUser;
import com.mydesk.api.generic.PageRequest;
import com.mydesk.api.post.dto.*;
import com.mydesk.api.post.service.PostService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class PostController {

    private final PostService postService;

    // TODO: json 파일을 내려주는걸로 할지
    @GetMapping("/api/v1/types")
    public Map<String, List<String>> getTypes() {
        return TypeResponseDto.getTypes();
    }

    @GetMapping("/api/v1/posts")
    public List<PostListResponseDto> getPostList(PageRequest pageRequest) {
        return postService.getPostList(pageRequest);
    }

    @ApiOperation(value="포스트 생성")
    @ApiResponses({
          @ApiResponse(code = 200, message = "성공"),
          @ApiResponse(code = 403, message = "권한 없음")
    })
    @PostMapping("/api/v1/posts")
    public Long create(@Valid @RequestBody PostCreateRequestDto requestDto) {
        return postService.create(requestDto);
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostResponseDto getPost(@PathVariable Long id) throws IllegalArgumentException {
        return postService.getPost(id);
    }

//    @PostMapping("/api/v1/manage/posts")
//    public Long createByAdmin(@Valid @RequestBody PostCreateRequestByAdminDto requestDto) {
//        return postService.createByAdmin(requestDto);
//    }
}

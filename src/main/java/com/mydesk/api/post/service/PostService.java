package com.mydesk.api.post.service;

import com.mydesk.api.config.auth.dto.SessionUser;
import com.mydesk.api.generic.PageRequest;
import com.mydesk.api.post.domain.*;
import com.mydesk.api.post.dto.*;
import com.mydesk.api.user.domain.User;
import com.mydesk.api.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PostService {

    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final PostQueryRepository postQueryRepository;

    @Transactional
    public Long create(SessionUser userDto, @Valid PostCreateRequestDto requestDto) {
        User user = userRepository.findById(userDto.getId())
                .orElseThrow(() -> new IllegalArgumentException("잘못된 사용자입니다."));
        Post post = requestDto.getPost();
        post.setUser(user);
        postRepository.save(post);

        return post.getId();
    }

    @Transactional
    public Long createByAdmin(PostCreateRequestByAdminDto requestDto) {
        User user = userRepository.findById(requestDto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("잘못된 사용자입니다."));
        Post post = requestDto.getPost();
        post.setUser(user);
        postRepository.save(post);

        return post.getId();
    }

    @Transactional(readOnly = true)
    public List<PostListResponseDto> getPostList(PageRequest pageRequest) {
        return postQueryRepository.getPostList(pageRequest.of());
    }


    @Transactional(readOnly = true)
    public PostResponseDto getPost(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시물이 없습니다"));

        return new PostResponseDto(post);
    }
}

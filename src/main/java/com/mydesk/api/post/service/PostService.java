package com.mydesk.api.post.service;

import com.mydesk.api.config.auth.dto.SessionUser;
import com.mydesk.api.post.domain.Post;
import com.mydesk.api.post.domain.PostQueryRepository;
import com.mydesk.api.post.domain.PostRepository;
import com.mydesk.api.post.dto.PostCreateRequestByAdminDto;
import com.mydesk.api.post.dto.PostCreateRequestDto;
import com.mydesk.api.post.dto.PostListResponseDto;
import com.mydesk.api.user.domain.User;
import com.mydesk.api.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostService {

    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final PostQueryRepository postQueryRepository;

    @Transactional
    public Long create(SessionUser userDto, PostCreateRequestDto requestDto) {
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
    public List<PostListResponseDto> getPostList() {
        return postQueryRepository.getPostList();
    }
}

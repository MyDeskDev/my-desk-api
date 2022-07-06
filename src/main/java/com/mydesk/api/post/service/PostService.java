package com.mydesk.api.post.service;

import com.mydesk.api.config.auth.dto.SessionUser;
import com.mydesk.api.post.domain.*;
import com.mydesk.api.post.dto.*;
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

    @Transactional
    public Long update(SessionUser userDto, Long id, PostUpdateRequestDto requestDto) throws Exception {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시물이 없습니다"));

        // TODO: exception 전역 처리
        if (post.getUser().getId().equals(userDto.getId())) {
            throw new IllegalAccessException("해당 게시물을 수정할 수 없습니다.");
        }

//        List<PostItem> postItems = postItemRepository.findByPostId(id);
//        postItems.forEach(System.out::println);
        return 1L;
    }

    @Transactional(readOnly = true)
    public PostResponseDto getPost(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시물이 없습니다"));

        return new PostResponseDto(post);
    }
}

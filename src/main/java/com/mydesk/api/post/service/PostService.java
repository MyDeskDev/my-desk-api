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
    public PostResponseDto update(SessionUser userDto, Long id, PostUpdateRequestDto requestDto) throws Exception {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시물이 없습니다"));

        if (!post.getUser().getId().equals(userDto.getId())) {
            throw new IllegalAccessException("해당 게시물을 수정할 수 없습니다.");
        }

        post.update(
                requestDto.getTitle(),
                requestDto.getPicture()
        );

        List<PostItem> postItems = post.getPostItems();
        List<PostItemUpdateRequestDto> postItemDtos = requestDto.getPostItems();
        for (PostItemUpdateRequestDto postItemDto: postItemDtos) {
            if (postItemDto.getId() == null) {
                post.addPostItem(PostItem.builder()
                                .name(postItemDto.getName())
                                .content(postItemDto.getContent())
                                .isFavorite(postItemDto.getIsFavorite())
                        .build());
            } else {
                PostItem postItem = postItems.stream()
                        .filter(pi -> pi.getId().equals(postItemDto.getId()))
                        .findAny()
                        .orElseThrow(() -> new IllegalArgumentException("해당 id의 아이템에는 접근할 수 없습니다"));
                postItem.update(
                        postItemDto.getName(),
                        postItemDto.getContent(),
                        postItemDto.getIsFavorite()
                );
            }
        }
        postRepository.save(post);

        return new PostResponseDto(post);
    }

    @Transactional(readOnly = true)
    public PostResponseDto getPost(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시물이 없습니다"));

        return new PostResponseDto(post);
    }
}

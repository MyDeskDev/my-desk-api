package com.mydesk.api.post.dto;

import com.mydesk.api.post.domain.Post;
import com.mydesk.api.post.domain.PostItem;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class PostListResponseDto {
    @Getter
    public class PostItemListResponseDto {
        private Long id;
        private String name;
        private String content;
        private Boolean isFavorite;

        public PostItemListResponseDto(PostItem postItem) {
            this.id = postItem.getId();
            this.name = postItem.getName();
            this.content = postItem.getContent();
            this.isFavorite = postItem.getIsFavorite();
        }
    }
    private Long id;
    private String title;
    private Long userId;
    private String userPicture;
    private List<PostItemListResponseDto> postItems;

    public PostListResponseDto(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.userId = post.getUser().getId();
        this.userPicture = post.getUser().getPicture();
        this.postItems = post.getPostItems().stream()
                .map(PostItemListResponseDto::new).collect(Collectors.toList());
    }
}

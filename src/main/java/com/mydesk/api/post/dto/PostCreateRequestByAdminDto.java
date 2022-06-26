package com.mydesk.api.post.dto;

import com.mydesk.api.post.domain.Post;
import com.mydesk.api.post.domain.PostItem;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class PostCreateRequestByAdminDto {
    private Long userId;
    private String title;
    private String picture;
    private List<PostItem> postItems;

    @Builder
    public PostCreateRequestByAdminDto(Long userId, String title, String picture, List<PostItem> postItems) {
        this.userId = userId;
        this.title = title;
        this.picture = picture;
        this.postItems = postItems;
    }

    public Post getPost() {
        Post post = Post.builder()
                .title(title)
                .picture(picture)
                .build();

        if (postItems != null) {
            post.addAllPostItem(postItems);
        }

        return post;
    }
}

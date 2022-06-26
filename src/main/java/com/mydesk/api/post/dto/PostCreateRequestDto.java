package com.mydesk.api.post.dto;

import com.mydesk.api.post.domain.Post;
import com.mydesk.api.post.domain.PostItem;
import com.mydesk.api.post.domain.PostStatus;
import com.mydesk.api.user.domain.User;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class PostCreateRequestDto {
    private String title;
    private String picture;
    private List<PostItem> postItems;

    @Builder
    public PostCreateRequestDto(String title, String picture, List<PostItem> postItems) {
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

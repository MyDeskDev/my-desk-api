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
    private List<PostItem> postItems = new ArrayList<>();

    @Builder
    public PostCreateRequestByAdminDto(Long userId, String title, String picture, List<PostItem> postItems) {
        this.userId = userId;
        this.title = title;
        this.picture = picture;
        this.postItems.addAll(postItems);
    }

    public Post getPost() {
        System.out.println(1);
        System.out.println(postItems);
        Post post = Post.builder()
                .title(title)
                .picture(picture)
                .build();
        post.addAllPostItem(postItems);

        return post;
    }
}

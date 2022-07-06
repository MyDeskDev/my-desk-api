package com.mydesk.api.post.dto;

import com.mydesk.api.post.domain.PostStatus;

public class PostUpdateRequestDto {
    private Long id;
    private String title;
    private String picture;
    private PostStatus postStatus;

    // TODO: postItem
    public PostUpdateRequestDto(Long id, String title, String picture, PostStatus postStatus) {
        this.id = id;
        this.title = title;
        this.picture = picture;
        this.postStatus = postStatus;
    }
}

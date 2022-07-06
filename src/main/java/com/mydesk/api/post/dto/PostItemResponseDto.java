package com.mydesk.api.post.dto;

import com.mydesk.api.post.domain.PostItem;
import lombok.Builder;
import lombok.Getter;

@Getter
public class PostItemResponseDto {
    private String name;
    private String content;
    private Boolean isFavorite;

    public PostItemResponseDto(PostItem entity) {
        this.name = entity.getName();
        this.content = entity.getContent();
        this.isFavorite = entity.getIsFavorite();
    }
}

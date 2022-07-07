package com.mydesk.api.post.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class PostItemUpdateRequestDto {
    private Long id;
    private String name;
    private String content;
    private Boolean isFavorite;

    @Builder
    public PostItemUpdateRequestDto(Long id, String name, String content, Boolean isFavorite) {
        this.id = id;
        this.name = name;
        this.content = content;
        this.isFavorite = isFavorite;
    }
}

package com.mydesk.api.post.dto;

import com.mydesk.api.post.domain.PostItem;
import lombok.Builder;

public class PostItemCreateDto {
    private String name;
    private String content;
    private Boolean isFavorite;

    @Builder
    public PostItemCreateDto(String name, String content, Boolean isFavorite) {
        this.name = name;
        this.content = content;
        this.isFavorite = isFavorite;
    }

    public PostItem toEntity(){
        return new PostItem().builder()
                .name(name)
                .content(content)
                .isFavorite(isFavorite)
                .build();
    }
}

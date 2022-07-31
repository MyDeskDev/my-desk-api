package com.mydesk.api.post.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class DeskItemUpdateRequestDto {
    private Long id;
    private String name;
    private String content;
    private Boolean isFavorite;

    @Builder
    public DeskItemUpdateRequestDto(Long id, String name, String content, Boolean isFavorite) {
        this.id = id;
        this.name = name;
        this.content = content;
        this.isFavorite = isFavorite;
    }
}

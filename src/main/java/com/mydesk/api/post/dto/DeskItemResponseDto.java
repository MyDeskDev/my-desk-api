package com.mydesk.api.post.dto;

import com.mydesk.api.post.domain.DeskItem;
import lombok.Getter;

@Getter
public class DeskItemResponseDto {
    private String name;
    private String content;
    private Boolean isFavorite;

    public DeskItemResponseDto(DeskItem entity) {
        this.name = entity.getName();
        this.content = entity.getContent();
        this.isFavorite = entity.getIsFavorite();
    }
}

package com.mydesk.api.post.dto;

import com.mydesk.api.post.domain.PostContent;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class DeskItemCreateDto {
    @NotNull
    private String name;
    @NotNull
    private String picture;
    @NotNull
    private String content;
    @NotNull
    private Boolean isFavorite;

    public DeskItemCreateDto(String name, String picture, String content, Boolean isFavorite) {
        this.name = name;
        this.picture = picture;
        this.content = content;
        this.isFavorite = isFavorite;
    }

    public PostContent toEntity(int contentOrder) {
        return PostContent.deskItem(name, picture, content, isFavorite, contentOrder);
    }
}

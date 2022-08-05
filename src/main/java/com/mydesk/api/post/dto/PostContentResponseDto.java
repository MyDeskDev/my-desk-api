package com.mydesk.api.post.dto;

import com.mydesk.api.post.domain.PostContent;
import lombok.Getter;

@Getter
public class PostContentResponseDto {
    private String name;
    private String picture;
    private String content;
    private Boolean isFavorite;
    private int contentOrder;

    public static PostContentResponseDto deskContentResponseDto(PostContent entity) {
        return new PostContentResponseDto(
                null,
                entity.getPicture(),
                entity.getContent(),
                null,
                entity.getContentOrder()
        );
    }

    public static PostContentResponseDto deskItemResponseDto(PostContent entity) {
        return new PostContentResponseDto(
                entity.getName(),
                entity.getPicture(),
                entity.getContent(),
                entity.getIsFavorite(),
                entity.getContentOrder()
        );
    }

    private PostContentResponseDto(String name, String picture, String content, Boolean isFavorite, int contentOrder) {
        this.name = name;
        this.picture = picture;
        this.content = content;
        this.isFavorite = isFavorite;
        this.contentOrder = contentOrder;
    }
}

package com.mydesk.api.post.dto;

import com.mydesk.api.post.domain.PostContent;
import lombok.Getter;

@Getter
public class PostContentResponseDto {
    private Long id;
    private String name;
    private String picture;
    private String content;
    private String purchaseLink;
    private Boolean isFavorite;
    private Boolean isRecommended;
    private int contentOrder;

    private PostContentResponseDto(
            Long id,
            String name,
            String picture,
            String content,
            String purchaseLink,
            Boolean isFavorite,
            Boolean isRecommended,
            int contentOrder
    ) {
        this.id = id;
        this.name = name;
        this.picture = picture;
        this.content = content;
        this.purchaseLink = purchaseLink;
        this.isFavorite = isFavorite;
        this.isRecommended = isRecommended;
        this.contentOrder = contentOrder;
    }

    public static PostContentResponseDto deskContentResponseDto(PostContent entity) {
        return new PostContentResponseDto(
                entity.getId(),
                null,
                entity.getPicture(),
                entity.getContent(),
                null,
                null,
                null,
                entity.getContentOrder()
        );
    }

    public static PostContentResponseDto deskItemResponseDto(PostContent entity) {
        return new PostContentResponseDto(
                entity.getId(),
                entity.getName(),
                entity.getPicture(),
                entity.getContent(),
                entity.getPurchaseLink(),
                entity.getIsFavorite(),
                entity.getIsRecommended(),
                entity.getContentOrder()
        );
    }
}

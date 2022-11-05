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

    private String purchaseLink;

    @NotNull(message = "isFavorite cannot be null")
    private Boolean isFavorite;

    @NotNull(message = "isRecommended cannot be null")
    private Boolean isRecommended;

    public DeskItemCreateDto(
            String name,
            String picture,
            String content,
            String purchaseLink,
            Boolean isFavorite,
            Boolean isRecommended
    ) {
        this.name = name;
        this.picture = picture;
        this.content = content;
        this.purchaseLink = purchaseLink;
        this.isFavorite = isFavorite;
        this.isRecommended = isRecommended;
    }

    public PostContent toEntity(int contentOrder) {
        return PostContent.deskItem(name, picture, content, purchaseLink, isFavorite, isRecommended, contentOrder);
    }
}

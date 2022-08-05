package com.mydesk.api.post.dto;

import com.mydesk.api.post.domain.PostContent;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class DeskContentCreateDto {
    @NotNull
    private String picture;
    @NotNull
    private String content;

    public DeskContentCreateDto(String picture, String content) {
        this.picture = picture;
        this.content = content;
    }

    public PostContent toEntity(int contentOrder) {
        return PostContent.deskContent(picture, content, contentOrder);
    }
}

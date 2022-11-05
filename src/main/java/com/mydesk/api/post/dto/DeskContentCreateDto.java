package com.mydesk.api.post.dto;

import com.mydesk.api.post.domain.ContentType;
import com.mydesk.api.post.domain.PostContent;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class DeskContentCreateDto {
    @NotNull
    private ContentType type;
    @NotNull
    private String value;

    public DeskContentCreateDto(ContentType type, String value) {
        this.type = type;
        this.value = value;
    }

    public PostContent toEntity(int contentOrder) {
        if (type.equals(ContentType.deskDescription)) {
            return PostContent.deskDescription(value, contentOrder);
        }
        if (type.equals(ContentType.deskPicture)) {
            return PostContent.deskPicture(value, contentOrder);
        }
        throw new IllegalArgumentException("잘못된 타입입니다.");
    }
}

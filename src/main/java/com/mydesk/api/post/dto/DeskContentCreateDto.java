package com.mydesk.api.post.dto;

import com.mydesk.api.post.domain.ContentType;
import com.mydesk.api.post.domain.PostContent;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class DeskContentCreateDto {
    @NotNull
    private String type;
    @NotNull
    private String value;

    public DeskContentCreateDto(String type, String value) {
        this.type = type;
        this.value = value;
    }

    public PostContent toEntity(int contentOrder) {
        if (this.type.equals(ContentType.deskDescription.getKey())) {
            return PostContent.deskDescription(value, contentOrder);
        }
        if (this.type.equals(ContentType.deskPicture.getKey())) {
            return PostContent.deskPicture(value, contentOrder);
        }
        throw new IllegalArgumentException("잘못된 타입입니다.");
    }
}

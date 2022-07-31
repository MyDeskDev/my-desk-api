package com.mydesk.api.post.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class PostUpdateRequestDto {
    private Long id;
    private String title;
    private String picture;
    private List<DeskItemUpdateRequestDto> deskItems;

    @Builder
    public PostUpdateRequestDto(Long id, String title, String picture, List<DeskItemUpdateRequestDto> deskItems) {
        this.id = id;
        this.title = title;
        this.picture = picture;
        this.deskItems = deskItems;
    }
}

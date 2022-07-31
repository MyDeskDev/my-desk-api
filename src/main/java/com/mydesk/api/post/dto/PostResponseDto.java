package com.mydesk.api.post.dto;

import com.mydesk.api.post.domain.Post;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class PostResponseDto {
    private Long id;
    private String title;
    private String picture;
    private List<DeskItemResponseDto> deskItems;

    public PostResponseDto(Post entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.picture = entity.getPicture();
        this.deskItems = entity.getDeskItems().stream().map(DeskItemResponseDto::new).collect(Collectors.toList());
    }
}

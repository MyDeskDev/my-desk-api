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
    private List<PostItemResponseDto> postItems;

    public PostResponseDto(Post entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.picture = entity.getPicture();
        this.postItems = entity.getPostItems().stream().map(PostItemResponseDto::new).collect(Collectors.toList());
    }
}

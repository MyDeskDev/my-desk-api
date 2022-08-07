package com.mydesk.api.post.dto;

import com.mydesk.api.post.domain.ContentType;
import com.mydesk.api.post.domain.Post;
import com.mydesk.api.post.domain.PostContent;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class PostResponseDto {
    private Long id;
    private String title;
    private String picture;
    private List<PostContentResponseDto> deskContents;
    private List<PostContentResponseDto> deskItems;

    public PostResponseDto(Post entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.picture = entity.getPicture();

        List<PostContent> postContents = entity.getPostContents();
        this.deskContents = postContents.stream()
                .filter(postContent -> !postContent.getContentType().equals(ContentType.item))
                .map(PostContentResponseDto::deskContentResponseDto)
                .collect(Collectors.toList());

        this.deskItems = postContents.stream()
                .filter(postContent -> postContent.getContentType().equals(ContentType.item))
                .map(PostContentResponseDto::deskItemResponseDto)
                .collect(Collectors.toList());
    }
}

package com.mydesk.api.post.dto;

import com.mydesk.api.post.domain.Post;
import com.mydesk.api.post.domain.DeskItem;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class PostCreateRequestDto {
    private String title;
    private String picture;
    private List<DeskItem> deskItems;

    @Builder
    public PostCreateRequestDto(String title, String picture, List<DeskItem> deskItems) {
        this.title = title;
        this.picture = picture;
        this.deskItems = deskItems;
    }

    public Post getPost() {
        Post post = Post.builder()
                .title(title)
                .picture(picture)
                .build();

        if (deskItems != null) {
            post.addAllDeskItem(deskItems);
        }

        return post;
    }
}

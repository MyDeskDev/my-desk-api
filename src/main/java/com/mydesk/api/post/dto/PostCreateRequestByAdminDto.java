package com.mydesk.api.post.dto;

import com.mydesk.api.post.domain.Post;
import com.mydesk.api.post.domain.PostContent;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
public class PostCreateRequestByAdminDto {
    @NotNull(message = "userId cannot be null")
    private Long userId;
    @NotNull(message = "title cannot be null")
    private String title;
    @NotNull(message = "picture cannot be null")
    private String picture;
    @NotEmpty(message = "deskContent cannot be null")
    private List<DeskContentCreateDto> deskContents;
    @NotEmpty(message = "deskItem cannot be null")
    private List<DeskItemCreateDto> deskItems;

    @Builder
    public PostCreateRequestByAdminDto(Long userId, String title, String picture,
                                       List<DeskContentCreateDto> deskContents, List<DeskItemCreateDto> deskItems) {
        this.userId = userId;
        this.title = title;
        this.picture = picture;
        this.deskContents = deskContents;
        this.deskItems = deskItems;
    }

    public Post getPost() {
        Post post = Post.builder()
                .title(title)
                .picture(picture)
                .build();

        int postOrder = 1;
        for (DeskContentCreateDto dto: deskContents) {
            post.addPostContent(dto.toEntity(postOrder));
            ++postOrder;
        }

        for (DeskItemCreateDto dto: deskItems) {
            post.addPostContent(dto.toEntity(postOrder));
            ++postOrder;
        }

        return post;
    }
}

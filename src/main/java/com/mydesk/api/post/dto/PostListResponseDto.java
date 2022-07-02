package com.mydesk.api.post.dto;
import lombok.Getter;

@Getter
public class PostListResponseDto {
    private Long id;
    private String title;
    private Long userId;
    private String userPicture;

    public PostListResponseDto(Long id, String title, Long userId, String userPicture) {
        this.id = id;
        this.title = title;
        this.userId = userId;
        this.userPicture = userPicture;
    }
}

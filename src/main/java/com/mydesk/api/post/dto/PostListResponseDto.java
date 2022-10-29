package com.mydesk.api.post.dto;
import lombok.Getter;

@Getter
public class PostListResponseDto {
    private Long id;
    private Long userId;
    private String userPicture;

    public PostListResponseDto(Long id, Long userId, String userPicture) {
        this.id = id;
        this.userId = userId;
        this.userPicture = userPicture;
    }
}

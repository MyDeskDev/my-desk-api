package com.mydesk.api.post.dto;
import lombok.Getter;

@Getter
public class PostListResponseDto {
    private Long id;
    private String deskSummary;
    private Long userId;
    private String userPicture;

    public PostListResponseDto(Long id, String deskSummary, Long userId, String userPicture) {
        this.id = id;
        this.deskSummary = deskSummary;
        this.userId = userId;
        this.userPicture = userPicture;
    }
}

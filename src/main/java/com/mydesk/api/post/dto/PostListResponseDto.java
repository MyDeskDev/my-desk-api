package com.mydesk.api.post.dto;
import lombok.Getter;

@Getter
public class PostListResponseDto {
    private Long id;
    private String thumbnailImgUrl;
    private String deskSummary;
    private Long userId;
    private String profileImgUrl;

    private String nickname;

    public PostListResponseDto(Long id, String thumbnailImgUrl, String deskSummary, Long userId, String profileImgUrl, String nickname) {
        this.id = id;
        this.thumbnailImgUrl = thumbnailImgUrl;
        this.deskSummary = deskSummary;
        this.userId = userId;
        this.profileImgUrl = profileImgUrl;
        this.nickname = nickname;
    }
}

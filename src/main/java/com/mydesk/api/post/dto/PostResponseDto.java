package com.mydesk.api.post.dto;

import com.mydesk.api.post.domain.ContentType;
import com.mydesk.api.post.domain.DeskConcept;
import com.mydesk.api.post.domain.Post;
import com.mydesk.api.post.domain.PostContent;
import com.mydesk.api.user.domain.BloodType;
import com.mydesk.api.user.domain.Gender;
import com.mydesk.api.user.domain.MBTI;
import com.mydesk.api.user.domain.User;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class PostResponseDto {
    private Long userId;
    private String profileImgUrl;
    private String nickname;
    private MBTI mbti;
    private Gender gender;
    private int age;
    private BloodType bloodType;
    private String countryCode;
    private String job;

    private Long id;
    private String thumbnailImgUrl;
    private String deskSummary;
    private String spaceType;
    private DeskConcept deskConcept;
    private int cost;
    private List<PostContentResponseDto> deskContents;
    private List<PostContentResponseDto> deskItems;

    public PostResponseDto(Post entity) {
        User user = entity.getUser();
        this.userId = user.getId();
        this.profileImgUrl = user.getProfileImgUrl();
        this.nickname = user.getNickname();
        this.mbti =  user.getMbti();
        this.gender = user.getGender();
        this.age = user.getAge();
        this.bloodType = user.getBloodType();
        this.countryCode = user.getCountryCode();
        this.job = user.getJob();

        this.id = entity.getId();
        this.thumbnailImgUrl = entity.getThumbnailImgUrl();
        this.deskSummary = entity.getDeskSummary();
        this.spaceType = entity.getSpaceType();
        this.deskConcept = entity.getDeskConcept();
        this.cost = entity.getCost();

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

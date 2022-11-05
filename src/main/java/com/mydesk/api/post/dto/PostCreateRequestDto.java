package com.mydesk.api.post.dto;

import com.mydesk.api.post.domain.ContentType;
import com.mydesk.api.post.domain.DeskConcept;
import com.mydesk.api.post.domain.Post;
import com.mydesk.api.user.domain.*;
import lombok.Builder;
import lombok.Getter;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
public class PostCreateRequestDto {
    @NotEmpty(message = "profileImgUrl cannot be null")
    private String profileImgUrl;

    @NotEmpty(message = "Name cannot be null")
    private String name;

    @NotEmpty(message = "Nickname cannot be null")
    private String nickname;

    @NotEmpty(message = "Email cannot be null")
    private String email;

    @NotNull(message = "Gender cannot be null")
    private Gender gender;

    @Min(value = 0, message = "Cost should greater than 0")
    private int age;

    private BloodType bloodType;

    private MBTI mbti;

    @NotEmpty(message = "countryCode cannot be null")
    private String countryCode;

    @NotEmpty(message = "Job cannot be null")
    private String job;


    private String thumbnailImgUrl;

    @NotNull(message = "DeskSummary cannot be null")
    private String deskSummary;

    @NotNull(message = "deskConcept cannot be null")
    private DeskConcept deskConcept;

    @NotNull(message = "SpaceType cannot be null")
    private String spaceType;

    @Min(value = 0, message = "Cost should greater than 0")
    private int cost;

    @Valid
    @NotEmpty(message = "deskContent cannot be null")
    private List<DeskContentCreateDto> deskContents;

    @Valid
    private List<DeskItemCreateDto> deskItems;

    @Builder
    public PostCreateRequestDto(
            String profileImgUrl,
            String name,
            String nickname,
            String email,
            Gender gender,
            int age,
            BloodType bloodType,
            MBTI mbti,
            String countryCode,
            String job,

            String thumbnailImgUrl,
            String deskSummary,
            DeskConcept deskConcept,
            String spaceType,
            int cost,
            List<DeskContentCreateDto> deskContents,
            List<DeskItemCreateDto> deskItems
    ) {
        this.profileImgUrl = profileImgUrl;
        this.name = name;
        this.nickname = nickname;
        this.email = email;
        this.gender = gender;
        this.age = age;
        this.bloodType = bloodType;
        this.mbti = mbti;
        this.countryCode = countryCode;
        this.job = job;

        this.thumbnailImgUrl = thumbnailImgUrl;
        this.deskSummary = deskSummary;
        this.deskConcept = deskConcept;
        this.spaceType = spaceType;
        this.cost = cost;
        this.deskContents = deskContents;
        this.deskItems = deskItems;
    }

    public Post getPost() {
        // 첫번째 책상 사진이 썸네일로
        for (DeskContentCreateDto dto: deskContents) {
            if (dto.getType().equals(ContentType.deskPicture)) {
                thumbnailImgUrl = dto.getValue();
                break;
            }
        }

        Post post = Post.builder()
                .thumbnailImgUrl(thumbnailImgUrl)
                .deskSummary(deskSummary)
                .deskConcept(deskConcept)
                .spaceType(spaceType)
                .cost(cost)
                .build();

        int postOrder = 1;
        for (DeskContentCreateDto dto: deskContents) {
            post.addPostContent(dto.toEntity(postOrder));
            ++postOrder;
        }

        if (!deskItems.isEmpty()) {
            for (DeskItemCreateDto dto: deskItems) {
                post.addPostContent(dto.toEntity(postOrder));
                ++postOrder;
            }
        }

        return post;
    }

    public User getUser() {
        return User.builder()
                .name(name)
                .profileImgUrl(profileImgUrl)
                .nickname(nickname)
                .email(email)
                .gender(gender)
                .age(age)
                .bloodType(bloodType)
                .mbti(mbti)
                .countryCode(countryCode)
                .job(job)
                .build();
    }
}

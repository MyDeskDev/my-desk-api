package com.mydesk.api.post.dto;

import com.mydesk.api.post.domain.DeskConcept;
import com.mydesk.api.post.domain.Post;
import com.mydesk.api.user.domain.*;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
public class PostCreateRequestDto {
    @NotEmpty(message = "profileImgUrl cannot be null")
    private String profileImage;
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
    @NotNull(message = "BloodType cannot be null")
    private BloodType bloodType;
    @NotNull(message = "MBTI cannot be null")
    private MBTI mbti;
    @NotEmpty(message = "countryCode cannot be null")
    private String countryCode;
    @NotEmpty(message = "Job cannot be null")
    private String job;

    @NotNull(message = "DeskSummary cannot be null")
    private String deskSummary;
    @NotNull(message = "deskConcept cannot be null")
    private DeskConcept deskConcept;
    @NotNull(message = "SpaceType cannot be null")
    private String spaceType;
    @Min(value = 0, message = "Cost should greater than 0")
    private int cost;
    @NotEmpty(message = "deskContent cannot be null")
    private List<DeskContentCreateDto> deskContents;
    @NotEmpty(message = "deskItem cannot be null")
    private List<DeskItemCreateDto> deskItems;

    @Builder
    public PostCreateRequestDto(
            String profileImage,
            String name,
            String nickname,
            String email,
            Gender gender,
            int age,
            BloodType bloodType,
            MBTI mbti,
            String countryCode,
            String job,

            String deskSummary,
            DeskConcept deskConcept,
            String spaceType,
            int cost,
            List<DeskContentCreateDto> deskContents,
            List<DeskItemCreateDto> deskItems
    ) {
        this.profileImage = profileImage;
        this.name = name;
        this.nickname = nickname;
        this.email = email;
        this.gender = gender;
        this.age = age;
        this.bloodType = bloodType;
        this.mbti = mbti;
        this.countryCode = countryCode;
        this.job = job;

        this.deskSummary = deskSummary;
        this.deskConcept = deskConcept;
        this.spaceType = spaceType;
        this.cost = cost;
        this.deskContents = deskContents;
        this.deskItems = deskItems;
    }

    public Post getPost() {
        Post post = Post.builder()
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

        for (DeskItemCreateDto dto: deskItems) {
            post.addPostContent(dto.toEntity(postOrder));
            ++postOrder;
        }

        return post;
    }

    public User getUser() {
        return User.builder()
                .name(name)
                .picture(profileImage)
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

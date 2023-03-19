package com.mydesk.api.post.dto;

import com.mydesk.api.post.domain.ContentType;
import com.mydesk.api.post.domain.DeskConcept;
import com.mydesk.api.post.domain.Post;
import com.mydesk.api.user.domain.BloodType;
import com.mydesk.api.user.domain.Gender;
import com.mydesk.api.user.domain.MBTI;
import com.mydesk.api.user.domain.User;
import lombok.Builder;
import lombok.Getter;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
public class PostTempSaveDto {
    private final String profileImgUrl;

    private final String name;

    private final String nickname;

    @NotEmpty(message = "Email cannot be null")
    private final String email;

    private final Gender gender;

    private final int age;

    private final BloodType bloodType;

    private final MBTI mbti;

    private final String countryCode;

    private final String job;

    private String thumbnailImgUrl;

    private final String deskSummary;

    private final DeskConcept deskConcept;

    private final String spaceType;

    private final int cost;

    @Valid
    @NotNull
    private final List<DeskContentCreateDto> deskContents;

    @Valid
    @NotNull
    private final List<DeskItemCreateDto> deskItems;

    @Builder
    public PostTempSaveDto(
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
        if (!deskContents.isEmpty()){
            for (DeskContentCreateDto dto: deskContents) {
                if (dto.getType().equals(ContentType.deskPicture)) {
                    thumbnailImgUrl = dto.getValue();
                    break;
                }
            }
        }

        Post post = Post.builder()
                .thumbnailImgUrl(thumbnailImgUrl)
                .deskSummary(deskSummary)
                .deskConcept(deskConcept)
                .spaceType(spaceType)
                .cost(cost)
                .build();

        if (!deskContents.isEmpty()) {
            int postOrder = 1;
            for (DeskContentCreateDto dto: deskContents) {
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

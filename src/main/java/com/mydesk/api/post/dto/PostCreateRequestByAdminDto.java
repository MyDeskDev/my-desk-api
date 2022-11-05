package com.mydesk.api.post.dto;

import com.mydesk.api.post.domain.DeskConcept;
import com.mydesk.api.post.domain.Post;
import com.mydesk.api.user.domain.BloodType;
import com.mydesk.api.user.domain.Gender;
import com.mydesk.api.user.domain.MBTI;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
public class PostCreateRequestByAdminDto {
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
    @Min(value = 0, message = "Age should greater than 0")
    private int age;
    @NotNull(message = "BloodType cannot be null")
    private BloodType bloodType;
    @NotNull(message = "MBTI cannot be null")
    private MBTI mbti;
    @NotEmpty(message = "Nationality cannot be null")
    private String nationality;

    @NotNull(message = "deskConcept cannot be null")
    private DeskConcept deskConcept;
    @NotNull(message = "SpaceType cannot be null")
    private String spaceType;
    @NotNull(message = "Cost cannot be null")
    private int cost;
    @NotEmpty(message = "deskContent cannot be null")
    private List<DeskContentCreateDto> deskContents;
    @NotEmpty(message = "deskItem cannot be null")
    private List<DeskItemCreateDto> deskItems;

    @Builder
    public PostCreateRequestByAdminDto(
            String profileImage,
            String name,
            String nickname,
            String email,
            Gender gender,
            int age,
            BloodType bloodType,
            MBTI mbti,
            String nationality,

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
        this.nationality = nationality;

        this.deskConcept = deskConcept;
        this.spaceType = spaceType;
        this.cost = cost;
        this.deskContents = deskContents;
        this.deskItems = deskItems;
    }

    public Post getPost() {
        Post post = Post.builder()
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
}

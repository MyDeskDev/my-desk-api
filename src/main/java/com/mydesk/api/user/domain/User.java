package com.mydesk.api.user.domain;

import com.mydesk.api.config.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="nickname")
    private String nickname;

    @Column(nullable = false, name="email")
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name="gender")
    private Gender gender;

    @Column(name="age")
    private int age;

    @Column(name="country_code")
    private String countryCode;

    @Column(name="job")
    private String job;

    @Enumerated(EnumType.STRING)
    @Column(name="blood_type")
    private BloodType bloodType;

    @Enumerated(EnumType.STRING)
    @Column(name="mbti")
    private MBTI mbti;

    @Column(name="profile_img_url")
    private String profileImgUrl;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name="role")
    private Role role = Role.GUEST;

    @Enumerated(EnumType.STRING)
    @Column(name="sns_channel")
    private SnsChannel snsChannel;

    @Builder
    public User(String name,
                String nickname,
                String email,
                Gender gender,
                int age,
                String countryCode,
                String job,
                BloodType bloodType,
                MBTI mbti,
                String profileImgUrl,
                SnsChannel snsChannel)
    {
        this.name = name;
        this.nickname = nickname;
        this.email = email;
        this.gender = gender;
        this.age = age;
        this.countryCode = countryCode;
        this.job = job;
        this.bloodType = bloodType;
        this.mbti = mbti;
        this.profileImgUrl = profileImgUrl;
        this.snsChannel = snsChannel;
    }

    public String getRoleKey() {
        return this.role.getKey();
    }

    public User update(String name, String profileImgUrl, SnsChannel snsChannel) {
        this.name = name;
        this.profileImgUrl = profileImgUrl;
        this.snsChannel = snsChannel;

        return this;
    }

    public void toUserRole() {
        this.role = Role.USER;
    }

    public void toAdminRole() {
        this.role = Role.ADMIN;
    }
}

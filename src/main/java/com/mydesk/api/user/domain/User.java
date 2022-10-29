package com.mydesk.api.user.domain;

import com.mydesk.api.config.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column
    private String nickname;

    @Column(nullable = false)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column
    private Gender gender;

    @Enumerated(EnumType.STRING)
    @Column
    private Age age;

    @Column
    private String nationality;

    @Enumerated(EnumType.STRING)
    @Column
    private BloodType bloodType;

    @Enumerated(EnumType.STRING)
    @Column
    private MBTI mbti;

    @Column(nullable = false)
    private String picture;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Enumerated(EnumType.STRING)
    @Column
    private SnsChannel snsChannel;

    @Builder
    public User(String name,
                String nickname,
                String email,
                Gender gender,
                Age age,
                String nationality,
                BloodType bloodType,
                MBTI mbti,
                String picture,
                Role role,
                SnsChannel snsChannel)
    {
        this.name = name;
        this.nickname = nickname;
        this.email = email;
        this.gender = gender;
        this.age = age;
        this.nationality = nationality;
        this.bloodType = bloodType;
        this.mbti = mbti;
        this.picture = picture;
        this.role = role;
        this.snsChannel = snsChannel;
    }

    public String getRoleKey() {
        return this.role.getKey();
    }

    public User update(String name, String picture) {
        this.name = name;
        this.picture = picture;

        return this;
    }
}

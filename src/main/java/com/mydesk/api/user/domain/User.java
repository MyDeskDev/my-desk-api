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
@ToString(of = {"id", "name", "email", "picture", "role", "snsChannel"})
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String picture;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Enumerated(EnumType.STRING)
    @Column
    private SnsChannel snsChannel;

    @Builder
    public User(String name, String email, String picture, Role role, SnsChannel snsChannel) {
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.role = role;
        this.snsChannel = snsChannel;
    }

    public User update(String name, String picture) {
        this.name = name;
        this.picture = picture;

        return this;
    }

    public String getRoleKey() {
        return this.role.getKey();
    }
}

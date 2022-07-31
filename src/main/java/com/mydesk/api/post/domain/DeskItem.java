package com.mydesk.api.post.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.mydesk.api.config.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@ToString(of = {"id", "name", "content", "isFavorite"})
public class DeskItem extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column(nullable = false)
    private Boolean isFavorite = false;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    public void setPost(Post post) {
        this.post = post;
    }

    @Builder
    public DeskItem(String name, String content, Boolean isFavorite) {
        this.name = name;
        this.content = content;
        this.isFavorite = isFavorite;
    }

    public void update(String name, String content, Boolean isFavorite) {
        this.name = name;
        this.content = content;
        this.isFavorite = isFavorite;
    }
}

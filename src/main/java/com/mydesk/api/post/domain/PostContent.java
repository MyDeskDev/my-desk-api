package com.mydesk.api.post.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.mydesk.api.config.BaseTimeEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class PostContent extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ContentType contentType;

    @Column
    private String name;

    @Column(nullable = false)
    private String picture;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(nullable = false)
    private Boolean isFavorite = false;

    @Column
    private int contentOrder;

    public static PostContent deskContent(String picture, String content, int contentOrder) {
        return new PostContent(ContentType.desk, null, content, picture, null, contentOrder);
    }

    public static PostContent deskItem(String name, String picture, String content, Boolean isFavorite, int contentOrder) {
        return new PostContent(ContentType.item, name, picture, content, isFavorite, contentOrder);
    }

    private PostContent(ContentType contentType, String name, String picture, String content,
                        Boolean isFavorite, int contentOrder) {
        this.contentType = contentType;
        this.name = name;
        this.picture = picture;
        this.content = content;
        this.isFavorite = isFavorite;
        this.contentOrder = contentOrder;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}

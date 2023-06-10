package com.mydesk.api.post.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.mydesk.api.config.BaseTimeEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Table(name = "\"post_content\"")
@Entity
public class PostContent extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name="content_type")
    private ContentType contentType;

    @Column(name="name")
    private String name;

    @Column(name="picture")
    private String picture;

    @Column(columnDefinition = "TEXT", name="content")
    private String content;

    @Column(name="purchase_link")
    private String purchaseLink;

    @Column(name="is_favorite")
    private Boolean isFavorite;

    @Column(name="is_recommended")
    private Boolean isRecommended;

    @Column(name="content_order")
    private int contentOrder;

    public static PostContent deskDescription(String content, int contentOrder) {
        return new PostContent(ContentType.deskDescription, null, null, content, null, null, null, contentOrder);
    }

    public static PostContent deskPicture(String picture, int contentOrder) {
        return new PostContent(ContentType.deskPicture, null, picture, null, null, null, null, contentOrder);
    }

    public static PostContent deskItem(
            String name,
            String picture,
            String content,
            String purchaseLink,
            Boolean isFavorite,
            Boolean isRecommended,
            int contentOrder
    ) {
        return new PostContent(ContentType.item, name, picture, content, purchaseLink, isFavorite, isRecommended, contentOrder);
    }

    private PostContent(
            ContentType contentType,
            String name,
            String picture,
            String content,
            String purchaseLink,
            Boolean isFavorite,
            Boolean isRecommended,
            int contentOrder
    ) {
        this.contentType = contentType;
        this.name = name;
        this.picture = picture;
        this.content = content;
        this.purchaseLink = purchaseLink;
        this.isFavorite = isFavorite;
        this.isRecommended = isRecommended;
        this.contentOrder = contentOrder;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}

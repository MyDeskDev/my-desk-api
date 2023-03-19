package com.mydesk.api.post.domain;

import com.mydesk.api.config.BaseTimeEntity;
import com.mydesk.api.user.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@NoArgsConstructor
@Entity
public class Post extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID uuid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name="thumbnail_img_url")
    private String thumbnailImgUrl;

    @Column(name="space_type")
    private String spaceType;

    @Enumerated(EnumType.STRING)
    @Column(name="desk_concept")
    private DeskConcept deskConcept;

    @Column(name="desk_summary")
    private String deskSummary;

    @Column(name="cost")
    private Integer cost;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "post")
    private List<PostContent> postContents = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @Column(name="status")
    private PostStatus status;

    @Column(name="post_order")
    private Long postOrder;

    public void setUser(User user) {
        this.user = user;
    }

    @Builder
    public Post(User user, String thumbnailImgUrl, String spaceType, DeskConcept deskConcept, String deskSummary, int cost, Long postOrder){
        this.user = user;
        this.thumbnailImgUrl = thumbnailImgUrl;
        this.spaceType = spaceType;
        this.deskConcept = deskConcept;
        this.deskSummary = deskSummary;
        this.cost = cost;
        this.status = PostStatus.CONFIRMING;
        this.postOrder = postOrder;
    }

    public void accept() {
        this.status = PostStatus.CONFIRMED;
    }

    public void reject() {
        this.status = PostStatus.REJECTED;
    }

    public void addPostContent(PostContent postContent) {
        getPostContents().add(postContent);
        postContent.setPost(this);
    }

    public void addAllPostContent(List<PostContent> postContents) {
        for (PostContent postContent : postContents) {
            this.addPostContent(postContent);
        }
    }

    public void validate() {
        if (this.thumbnailImgUrl.isEmpty()) {
            throw new IllegalArgumentException("Thumbnail image url is required field");
        }

        if (this.spaceType.isEmpty()) {
            throw new IllegalArgumentException("Space type is required field");
        }

        if (this.deskSummary.isEmpty()) {
            throw new IllegalArgumentException("Desk summary is required field");
        }

        if (this.cost == null) {
            throw new IllegalArgumentException("Cost is required field");
        }
    }
}

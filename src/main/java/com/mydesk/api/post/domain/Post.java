package com.mydesk.api.post.domain;

import com.mydesk.api.config.BaseTimeEntity;
import com.mydesk.api.user.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Post extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "post")
    private List<PostContent> postContents = new ArrayList<>();

    @Column
    private String picture;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PostStatus status;

    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long postOrder;

    public void setUser(User user) {
        this.user = user;
    }

    @Builder
    public Post(User user, String title, String picture, Long postOrder){
        this.user = user;
        this.title = title;
        this.picture = picture;
        this.status = PostStatus.CONFIRMING;
        this.postOrder = postOrder;
    }

    public void update(String title, String picture) {
        this.title = title;
        this.picture = picture;
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
}

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

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "post")
    private List<PostItem> postItems;

    @Column
    private String picture;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PostStatus status;

    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ordering;

    public void setUser(User user) {
        this.user = user;
    }

    @Builder
    public Post(User user, String title, Long ordering, String picture){
        this.user = user;
        this.title = title;
        this.picture = picture;
        this.status = PostStatus.CONFIRMING;
        this.ordering = ordering;
        this.postItems = new ArrayList<>();
    }

    @Builder
    public Post(String title, Long ordering, String picture){
        this.title = title;
        this.picture = picture;
        this.status = PostStatus.CONFIRMING;
        this.ordering = ordering;
        this.postItems = new ArrayList<>();
    }

    public void accept() {
        this.status = PostStatus.CONFIRMED;
    }

    public void reject() {
        this.status = PostStatus.REJECTED;
    }

    public void addPostItem(PostItem postItem) {
        getPostItems().add(postItem);
        postItem.setPost(this);
    }

    public void addAllPostItem(List<PostItem> postItems) {
        for (PostItem postItem: postItems) {
            this.addPostItem(postItem);
        }
    }
}

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
//@ToString(of = {"id", "title", "picture", "status", "postOrder"})
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
    private List<DeskContent> deskContents = new ArrayList<>();

    @Column(nullable = false)
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "post")
    private List<DeskItem> deskItems = new ArrayList<>();

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

    @Builder
    public Post(String title, String picture, Long postOrder){
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

    public void addDeskItem(DeskItem deskItem) {
        getDeskItems().add(deskItem);
        deskItem.setPost(this);
    }

    public void addAllDeskItem(List<DeskItem> deskItems) {
        for (DeskItem deskItem : deskItems) {
            this.addDeskItem(deskItem);
        }
    }

    public void addDeskContent(DeskContent deskContent) {
        getDeskContents().add(deskContent);
        deskContent.setPost(this);
    }

    public void addAllDeskContent(List<DeskContent> deskContents) {
        for (DeskContent deskContent : deskContents) {
            this.addDeskContent(deskContent);
        }
    }
}

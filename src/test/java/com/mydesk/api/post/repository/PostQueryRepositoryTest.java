package com.mydesk.api.post.repository;

import com.mydesk.api.post.domain.DeskConcept;
import com.mydesk.api.post.domain.Post;
import com.mydesk.api.post.domain.PostContent;
import com.mydesk.api.post.domain.PostRepository;
import com.mydesk.api.user.domain.*;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;

import static com.mydesk.api.post.domain.QPost.post;
import static com.mydesk.api.user.domain.QUser.user;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class PostQueryRepositoryTest {
    @Autowired
    PostRepository postRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    JPAQueryFactory queryFactory;

    @BeforeEach
    public void setup() {
        User user1 = User.builder()
                .name("양현철")
                .nickname("yhc")
                .email("hyunchul.yang@gmail.com")
                .picture("testPicture")
                .bloodType(BloodType.A)
                .mbti(MBTI.INFJ)
                .snsChannel(SnsChannel.GOOGLE)
                        .build();
        userRepository.save(user1);

        System.out.println("user1.getId() = " + user1.getId());

        Post post1 = Post.builder()
                .user(user1)
                .spaceType("원룸")
                .deskConcept(DeskConcept.CLASSIC)
                .deskSummary("제 원룸책상입니다.")
                .cost(10)
                .postOrder(1L)
                .build();
        Post post2 = Post.builder()
                .user(user1)
                .spaceType("원룸2")
                .deskConcept(DeskConcept.MODERN)
                .deskSummary("제 원룸책상2입니다.")
                .postOrder(2L)
                .cost(20)
                .build();
        Post post3 = Post.builder()
                .user(user1)
                .spaceType("원룸3")
                .deskConcept(DeskConcept.MODERN)
                .deskSummary("제 원룸책상3입니다.")
                .cost(20)
                .build();

        PostContent deskItem1 = PostContent.deskPicture("deskPicture", 1);
        PostContent deskItem2 = PostContent.deskDescription("deskPicture", 2);
        PostContent deskItem3 = PostContent.deskItem("아이템2", "itemPicture2", "아이템 설명2", false, 3);

        post1.addPostContent(deskItem1);
        post2.addAllPostContent(List.of(deskItem2, deskItem3));
        postRepository.saveAll(List.of(post1, post2, post3));
    }

    @AfterEach
    public void tearDown() {
        postRepository.deleteAll();
    }

    @Test
    public void basic_query() {
        List<Post> results = queryFactory
                .selectFrom(post)
                .fetch();

        assertThat(results.size()).isEqualTo(3);
    }

    @Test
    public void sort_query() {
        List<Post> results = queryFactory
                .selectFrom(post)
                .orderBy(post.postOrder.asc().nullsLast())
                .fetch();

        assertThat(results.get(0).getSpaceType()).isEqualTo("원룸");
    }

    @Test
    public void paging_query() {
        List<Post> results = queryFactory
                .selectFrom(post)
                .orderBy(post.postOrder.asc().nullsLast())
                .offset(0)
                .limit(10)
                .fetch();

        assertThat(results.get(0).getSpaceType()).isEqualTo("원룸");
        assertThat(results.get(2).getSpaceType()).isEqualTo("원룸3");
        assertThat(results.size()).isEqualTo(3);
    }

    @Test
    @Transactional
    public void join() {
        List<Post> results = queryFactory
                .selectFrom(post)
                .join(post.user, user)
                .fetch();

        System.out.println(results.get(0).getUser());
    }
}

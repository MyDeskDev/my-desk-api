package com.mydesk.api.post.repository;

import com.mydesk.api.post.domain.Post;
import com.mydesk.api.post.domain.PostItem;
import com.mydesk.api.post.domain.PostRepository;
import com.mydesk.api.user.domain.Role;
import com.mydesk.api.user.domain.SnsChannel;
import com.mydesk.api.user.domain.User;
import com.mydesk.api.user.domain.UserRepository;
import com.querydsl.core.types.Projections;
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
        User user1 = userRepository.save(new User("yhc", "hyunchul.yang@gmail.com", "testPicture", Role.USER, SnsChannel.GOOGLE));
        System.out.println("user1.getId() = " + user1.getId());

        Post post1 = new Post(user1, "제목1", "pic1", 3L);
        Post post2 = new Post("제목2", "pic2", 2L);
        Post post3 = new Post("제목3", "pic3", 1L);

        PostItem postItem1 = new PostItem("아이템1", "설명1", true);
        PostItem postItem2 = new PostItem("아이템2", "설명2", false);
        PostItem postItem3 = new PostItem("아이템3", "설명3", true);

        post1.addPostItem(postItem1);
        post2.addAllPostItem(List.of(postItem2, postItem3));
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
                .orderBy(post.postOrder.asc())
                .fetch();

        assertThat(results.get(0).getTitle()).isEqualTo("제목3");
    }

    @Test
    public void paging_query() {
        List<Post> results = queryFactory
                .selectFrom(post)
                .orderBy(post.postOrder.asc())
                .offset(0)
                .limit(10)
                .fetch();

        assertThat(results.get(0).getTitle()).isEqualTo("제목3");
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

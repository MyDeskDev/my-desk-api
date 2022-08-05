package com.mydesk.api.post.repository;

import com.mydesk.api.post.domain.Post;
import com.mydesk.api.post.domain.PostContent;
import com.mydesk.api.post.domain.PostRepository;
import com.mydesk.api.user.domain.Role;
import com.mydesk.api.user.domain.SnsChannel;
import com.mydesk.api.user.domain.User;
import com.mydesk.api.user.domain.UserRepository;
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
        Post post2 = new Post(user1, "제목2", "pic2", 2L);
        Post post3 = new Post(user1, "제목3", "pic3", 1L);

        PostContent deskItem1 = PostContent.deskContent("deskPicture", "설명1", 1);
        PostContent deskItem2 = PostContent.deskItem("아이템1", "itemPicture", "아이템 설명", true, 2);
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

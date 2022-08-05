package com.mydesk.api.post.domain;

import static com.mydesk.api.Fixtures.*;

import com.mydesk.api.user.domain.User;
import com.mydesk.api.user.domain.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;

import static com.mydesk.api.Fixtures.aDeskItem;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class PostTest {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    private User user;

    @BeforeEach
    public void setup() {
        this.user = userRepository.save(aUser().build());
    }

    @AfterEach
    public void tearDown() {
        postRepository.deleteAll();
    }

    @Test
    @Transactional
    public void postItemRelationTest() {
        // given
        Post post = aPost().build();
        post.setUser(user);

        // when
        PostContent postContent = aDeskContent();
        PostContent deskItem = aDeskItem();
        post.addPostContent(postContent);
        post.addPostContent(deskItem);

        // then
        assertThat(post.getPostContents().size()).isEqualTo(2);
        assertThat(deskItem.getPost().getId()).isEqualTo(post.getId());
    }

    @Test
    @Transactional
    public void defaultStatusTest() {
        // given
        Post post = aPost().build();
        post.setUser(this.user);
        postRepository.save(post);

        // when
        List<Post> posts = postRepository.findAll();
        Post resultPost = posts.get(0);

        // then
        assertThat(resultPost.getStatus()).isEqualTo(PostStatus.CONFIRMING);
    }

    @Test
    @Transactional
    public void postAcceptTest() {
        // given
        Post post = aPost().user(user).build();
        post.accept();
        Post expectedPost = postRepository.save(post);

        // when
        Post acceptedPost = postRepository.findById(expectedPost.getId()).orElseThrow(IllegalArgumentException::new);

        // then
        assertThat(acceptedPost.getStatus()).isEqualTo(PostStatus.CONFIRMED);
    }
}

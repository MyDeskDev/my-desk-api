package com.mydesk.api.user.domain;

import static com.mydesk.api.Fixtures.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class UserTest {

    @Autowired
    UserRepository userRepository;

    @AfterEach
    public void cleanup() {
        userRepository.deleteAll();
    }

    @Test
    public void userBuilderTest() {
        // given
        userRepository.save(aUser().build());

        // when
        List<User> users = userRepository.findAll();

        //then
        User user = users.get(0);
        assertThat(user.getName()).isEqualTo("양현철");
        assertThat(user.getEmail()).isEqualTo("hyunchul.yang@gmail.com");
        assertThat(user.getSnsChannel()).isEqualTo(SnsChannel.GOOGLE);
    }
}

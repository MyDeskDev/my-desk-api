package com.mydesk.api.post.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mydesk.api.config.auth.dto.SessionUser;
import com.mydesk.api.post.domain.DeskItem;
import com.mydesk.api.post.domain.Post;
import com.mydesk.api.post.domain.PostRepository;
import com.mydesk.api.post.domain.PostStatus;
import com.mydesk.api.post.dto.PostCreateRequestByAdminDto;
import com.mydesk.api.post.dto.PostCreateRequestDto;
import com.mydesk.api.post.dto.DeskItemUpdateRequestDto;
import com.mydesk.api.post.dto.PostUpdateRequestDto;
import com.mydesk.api.user.domain.Role;
import com.mydesk.api.user.domain.User;
import com.mydesk.api.user.domain.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static com.mydesk.api.Fixtures.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PostControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private WebApplicationContext context;

    private MockHttpSession session;
    private MockMvc mvc;

    @BeforeEach
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @AfterEach
    public void tearDown() throws Exception {
        postRepository.deleteAll();
        userRepository.deleteAll();
    }

    private void setSessionUser() {
        User user = userRepository.save(aUser().role(Role.USER).build());
        session = new MockHttpSession();
        session.setAttribute("user", new SessionUser(user));
    }

    private void setAdminUser() {
        User user = userRepository.save(aUser().role(Role.ADMIN).build());
        session = new MockHttpSession();
        session.setAttribute("user", new SessionUser(user));
    }

    @Test
    @WithMockUser(roles="USER")
    public void submitPost() throws Exception {
        // given
        String title = "title";
        String picture = "picture";
        DeskItem deskItem1 = aDeskItem().build();
        DeskItem deskItem2 = aDeskItem2().build();
        List<DeskItem> deskItems = new ArrayList<>();
        deskItems.add(deskItem1);
        deskItems.add(deskItem2);
        PostCreateRequestDto requestDto = PostCreateRequestDto.builder()
                .title(title)
                .picture(picture)
                .deskItems(deskItems)
                .build();

        String url = "http://localhost:" + port + "/api/v1/post";

        // when
        setSessionUser();
        mvc.perform(post(url).session(session)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(requestDto)))
                .andExpect(status().isOk());

        // then
        List<Post> all = postRepository.findAll();
        assertThat(all.get(0).getUser()).isNotNull();
        assertThat(all.get(0).getStatus()).isEqualTo(PostStatus.CONFIRMING);
    }

    @Test
    @WithMockUser(roles="ADMIN")
    public void addPostByAdmin() throws Exception {
        // given
        User user = userRepository.save(aUser().role(Role.USER).build());
        String title = "title";
        String picture = "picture";
        DeskItem deskItem1 = aDeskItem().build();
        DeskItem deskItem2 = aDeskItem2().build();
        List<DeskItem> deskItems = new ArrayList<>();
        deskItems.add(deskItem1);
        deskItems.add(deskItem2);
        PostCreateRequestByAdminDto requestDto = PostCreateRequestByAdminDto.builder()
                .userId(user.getId())
                .title(title)
                .picture(picture)
                .deskItems(deskItems)
                .build();

        String url = "http://localhost:" + port + "/api/v1/manage/post";

        // when
        setAdminUser();
        mvc.perform(post(url).session(session)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(requestDto)))
                .andExpect(status().isOk());

        // then
        List<Post> all = postRepository.findAll();
        assertThat(all.get(0).getUser()).isNotNull();
        assertThat(all.get(0).getStatus()).isEqualTo(PostStatus.CONFIRMING);
    }

    @Test
    @WithMockUser(roles="USER")
    public void addPostFailedByAuthorization() throws Exception {
        // given
        User user = userRepository.save(aUser().role(Role.USER).build());
        String title = "title";
        String picture = "picture";
        PostCreateRequestByAdminDto requestDto = PostCreateRequestByAdminDto.builder()
                .userId(user.getId())
                .title(title)
                .picture(picture)
                .build();

        String url = "http://localhost:" + port + "/api/v1/manage/post";

        // when
        setSessionUser();
        mvc.perform(post(url).session(session)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(requestDto)))
                .andExpect(status().isForbidden());

        // then
        List<Post> all = postRepository.findAll();
        assertThat(all.size()).isEqualTo(0);
    }

    @Test
    @Transactional
    @WithMockUser(roles="USER")
    public void updatePost() throws Exception {
        // given
        setSessionUser();
        User user = userRepository.findAll().get(0);
        Post post = new Post(user, "title1", "testPicture", 1L);
        DeskItem deskItem = new DeskItem("name1", "content1", true);
        post.addDeskItem(deskItem);
        postRepository.save(post);

        String newTitle = "new title";
        String newPicture = "new picture";
        String newPostItemName = "new postItem name";
        DeskItemUpdateRequestDto itemUpdateRequestDto1 = DeskItemUpdateRequestDto.builder()
                .name("new item add")
                .content("new content")
                .isFavorite(true)
                .build();
        DeskItemUpdateRequestDto itemUpdateRequestDto2 = DeskItemUpdateRequestDto.builder()
                .id(deskItem.getId())
                .name(newPostItemName)
                .content("new content")
                .isFavorite(true)
                .build();
        List<DeskItemUpdateRequestDto> itemUpdateRequestDtoList = new ArrayList<>();
        itemUpdateRequestDtoList.addAll(List.of(itemUpdateRequestDto1, itemUpdateRequestDto2));
        PostUpdateRequestDto requestDto = PostUpdateRequestDto.builder()
                .id(post.getId())
                .title(newTitle)
                .picture(newPicture)
                .deskItems(itemUpdateRequestDtoList)
                .build();

        // when
        String url = "http://localhost:" + port + "/api/v1/post/" + post.getId();
        mvc.perform(put(url).session(session)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(requestDto)))
                .andExpect(status().isOk());

        Post updatedPost = postRepository.findAll().get(0);
        DeskItem updatedDeskItem = updatedPost.getDeskItems().get(0);
        assertThat(updatedPost.getTitle()).isEqualTo(newTitle);
        assertThat(updatedPost.getPicture()).isEqualTo(newPicture);
        assertThat(updatedPost.getDeskItems().size()).isEqualTo(2);
        assertThat(updatedDeskItem.getName()).isEqualTo(newPostItemName);
    }
}

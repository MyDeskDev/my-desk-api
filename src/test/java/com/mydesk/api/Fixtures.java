package com.mydesk.api;

import com.mydesk.api.post.domain.PostStatus;
import com.mydesk.api.user.domain.Role;
import com.mydesk.api.user.domain.SnsChannel;
import com.mydesk.api.user.domain.User;
import com.mydesk.api.user.domain.User.UserBuilder;
import com.mydesk.api.post.domain.Post;
import com.mydesk.api.post.domain.Post.PostBuilder;
import com.mydesk.api.post.domain.PostItem;
import com.mydesk.api.post.domain.PostItem.PostItemBuilder;

public class Fixtures {
    public static UserBuilder aUser() {
        return User.builder()
                .name("yhc")
                .email("hyunchul.yang@gmail.com")
                .picture("pic")
                .role(Role.USER)
                .snsChannel(SnsChannel.GOOGLE);
    }

    public static PostItemBuilder aPostItem() {
        return PostItem.builder()
                .name("마우스")
                .content("좋은 마우스입니다.")
                .isFavorite(true);
    }

    public static PostItemBuilder aPostItem2() {
        return PostItem.builder()
                .name("모니터")
                .content("큰 모니터입니다.")
                .isFavorite(false);
    }

    public static PostBuilder aPost() {
        return Post.builder()
                .title("test")
                .picture("pic")
                .postOrder(1L);
    }
}
package com.mydesk.api;

import com.mydesk.api.post.domain.PostContent;
import com.mydesk.api.user.domain.Role;
import com.mydesk.api.user.domain.SnsChannel;
import com.mydesk.api.user.domain.User;
import com.mydesk.api.user.domain.User.UserBuilder;
import com.mydesk.api.post.domain.Post;
import com.mydesk.api.post.domain.Post.PostBuilder;

public class Fixtures {
    public static UserBuilder aUser() {
        return User.builder()
                .name("yhc")
                .email("hyunchul.yang@gmail.com")
                .picture("pic")
                .role(Role.USER)
                .snsChannel(SnsChannel.GOOGLE);
    }

    public static PostContent aDeskContent() {
        return PostContent.deskDescription( "deskContent", 1);
    }

    public static PostContent aDeskItem() {
        return PostContent.deskItem("itemName", "itemPicture", "itemContent", true, 2);
    }

    public static PostBuilder aPost() {
        return Post.builder()
                .title("test")
                .picture("pic")
                .postOrder(1L);
    }
}
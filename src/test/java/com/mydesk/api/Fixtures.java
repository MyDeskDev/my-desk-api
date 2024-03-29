package com.mydesk.api;

import com.mydesk.api.post.domain.DeskConcept;
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
                .email("hyunchul.yang@gmail.com");
    }

    public static PostContent aDeskContent() {
        return PostContent.deskDescription( "deskContent", 1);
    }

    public static PostContent aDeskItem() {
        return PostContent.deskItem("itemName", "itemPicture", "itemContent", "www.naver.com", true, false, 2);
    }

    public static PostBuilder aPost() {
        return Post.builder();
    }

    public static PostBuilder CompletedPost() {
        return Post.builder()
                .spaceType("원룸3")
                .deskConcept(DeskConcept.MODERN)
                .deskSummary("제 원룸책상3입니다.")
                .cost(20)
                .postOrder(1L);
    }
}
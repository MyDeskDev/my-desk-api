package com.mydesk.api.post.domain;

import com.mydesk.api.post.dto.PostListResponseDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.mydesk.api.post.domain.QPost.*;
import static com.mydesk.api.user.domain.QUser.user;

@RequiredArgsConstructor
@Repository
public class PostQueryRepository {
    private final JPAQueryFactory queryFactory;

    public List<PostListResponseDto> getPostList(Pageable pageable) {
        return queryFactory
                .select(Projections.constructor(PostListResponseDto.class,
                        post.id,
                        post.title,
                        post.picture,
                        post.user.id,
                        post.user.picture))
                .from(post)
                .join(post.user, user)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }
}

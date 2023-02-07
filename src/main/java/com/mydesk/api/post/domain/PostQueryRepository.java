package com.mydesk.api.post.domain;

import com.mydesk.api.post.dto.PostListResponseDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
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

    public List<PostListResponseDto> getPosts(Pageable pageable) {
        return queryFactory
                .select(Projections.constructor(PostListResponseDto.class,
                        post.id,
                        post.thumbnailImgUrl,
                        post.deskSummary,
                        post.user.id,
                        post.user.profileImgUrl,
                        post.user.nickname))
                .from(post)
                .join(post.user, user)
                .orderBy(post.postOrder.asc().nullsLast())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }

    public List<PostListResponseDto> getPostsByStatus(PostStatus postStatus, Pageable pageable) {
        return queryFactory
                .select(Projections.constructor(PostListResponseDto.class,
                        post.id,
                        post.thumbnailImgUrl,
                        post.deskSummary,
                        post.user.id,
                        post.user.profileImgUrl,
                        post.user.nickname))
                .from(post)
                .where(post.status.eq(postStatus))
                .join(post.user, user)
                .orderBy(post.postOrder.asc().nullsLast())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }
}

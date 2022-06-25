package com.mydesk.api.post.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PostStatus {

    CONFIRMING("CONFIRMING", "승인 대기중"),
    CONFIRMED("CONFIRMED", "승인완료"),
    REJECTED("REJECTED", "승인거부"),
    HIDDEN("HIDDEN", "숨김");

    private final String key;
    private final String name;
}

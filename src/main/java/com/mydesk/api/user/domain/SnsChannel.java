package com.mydesk.api.user.domain;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum SnsChannel {

    GOOGLE("GOOGLE", "구글"),
    NAVER("NAVER", "네이버"),
    KAKAO("KAKAO", "카카오");

    private final String key;
    private final String name;
}

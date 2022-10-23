package com.mydesk.api.user.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Getter
@RequiredArgsConstructor
public enum Gender {
    Male("남"),
    Female("여");

    private final String korean;
}

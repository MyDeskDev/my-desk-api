package com.mydesk.api.user.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Age {

    TWENTIES("20대 이하"), // 20대 이하
    THIRTIES("30대"),
    FORTIES("40대"),
    FIFTIES("50대 이상"); // 50대 이상

    private final String korean;
}
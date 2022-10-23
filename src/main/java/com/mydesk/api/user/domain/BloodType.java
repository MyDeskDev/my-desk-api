package com.mydesk.api.user.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum BloodType {

    A("A형"),
    B("B형"),
    O("O형"),
    AB("AB형");

    private final String korean;
}

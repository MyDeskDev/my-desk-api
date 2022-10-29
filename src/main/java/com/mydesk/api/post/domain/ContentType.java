package com.mydesk.api.post.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ContentType {

    deskPicture("deskPicture", "책상사진"),
    deskDescription("deskDescription", "책상 설명"),
    item("item", "아이템");

    private final String key;
    private final String type;
}

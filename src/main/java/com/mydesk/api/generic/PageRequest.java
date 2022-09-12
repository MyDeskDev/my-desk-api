package com.mydesk.api.generic;

import lombok.Getter;

@Getter
public class PageRequest {
    private Integer page;
    private Integer size;
    private final int MAX_SIZE = 50;
    private final int DEFAULT_SIZE = 20;

    public PageRequest(Integer page, Integer size) {
        if (page == null) {
            this.page = 1;
        } else {
            this.page = page;
        }
        if (size == null || size > MAX_SIZE) {
            this.size = DEFAULT_SIZE;
        } else {
            this.size = size;
        }
    }

    public org.springframework.data.domain.PageRequest of() {
        return org.springframework.data.domain.PageRequest.of(page - 1, size);
    }
}

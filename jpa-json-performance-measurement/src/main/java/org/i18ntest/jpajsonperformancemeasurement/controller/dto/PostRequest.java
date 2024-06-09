package org.i18ntest.jpajsonperformancemeasurement.controller.dto;

import org.i18ntest.jpajsonperformancemeasurement.domain.JPAPost;
import org.i18ntest.jpajsonperformancemeasurement.domain.JSONPost;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public record PostRequest(
        String title,
        String content
) {
    public JPAPost toEntity() {
        return JPAPost.builder()
                .title(title)
                .content(content)
                .build();
    }
    public JSONPost toJSONEntity() {
        return JSONPost.builder()
                .title(title)
                .content(content)
                .votes(new ConcurrentHashMap<>())
                .build();
    }
}

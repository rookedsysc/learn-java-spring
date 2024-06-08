package org.i18ntest.jpajsonperformancemeasurement.controller.dto;

import org.i18ntest.jpajsonperformancemeasurement.domain.JPAPost;

public record JPAPostRequest(
        String title,
        String content
) {
    public JPAPost toEntity() {
        return JPAPost.builder()
                .title(title)
                .content(content)
                .build();
    }
}

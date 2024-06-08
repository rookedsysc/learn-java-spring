package org.i18ntest.jpajsonperformancemeasurement.controller.dto;


import lombok.*;
import org.i18ntest.jpajsonperformancemeasurement.domain.JPAPost;
import org.i18ntest.jpajsonperformancemeasurement.domain.dto.VoteResponse;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class JPAPostResponse {
    private Long id;
    private String title;
    private String content;
    private List<VoteResponse> vote;

    public static JPAPostResponse fromEntity(JPAPost post) {
        return JPAPostResponse.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .vote(post.getVote()
                        .stream()
                        .map(VoteResponse::fromEntity)
                        .collect(Collectors.toList()))
                .build();
    }

}

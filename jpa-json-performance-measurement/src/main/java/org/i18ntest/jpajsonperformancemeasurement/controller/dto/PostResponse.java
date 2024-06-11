package org.i18ntest.jpajsonperformancemeasurement.controller.dto;


import lombok.*;
import org.i18ntest.jpajsonperformancemeasurement.domain.JPAPost;
import org.i18ntest.jpajsonperformancemeasurement.domain.JSONPost;
import org.i18ntest.jpajsonperformancemeasurement.domain.dto.VoteResponse;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class PostResponse {
    private Long id;
    private String title;
    private String content;
    private List<VoteResponse> vote;

    public static PostResponse fromEntity(JPAPost post) {
        return PostResponse.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .vote(post.getVote()
                        .stream()
                        .map(VoteResponse::fromEntity)
                        .collect(Collectors.toList()))
                .build();
    }

    public static PostResponse fromEntity(JSONPost post) {
        return PostResponse.builder()
                .vote(post.getVotes()
                        .entrySet()
                        .stream()
                        .map(e -> {
                            return VoteResponse.builder()
                                    .memberId(e.getKey())
                                    .vote(e.getValue())
                                    .build();
                        })
                        .collect(Collectors.toList()))
                .title(post.getTitle())
                .content(post.getContent())
                .id(post.getId())
                .build();
    }
}

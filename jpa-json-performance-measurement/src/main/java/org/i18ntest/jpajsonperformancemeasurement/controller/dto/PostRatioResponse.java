package org.i18ntest.jpajsonperformancemeasurement.controller.dto;

import lombok.*;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class PostRatioResponse {
    private Long id;
    private String title;
    private String content;
    private List<VoteRatio> votes;

    @Getter
    @Builder
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    public static class VoteRatio {
        private Boolean voteOption;
        private Double ratio;
    }
}

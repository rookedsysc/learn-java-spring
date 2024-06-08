package org.i18ntest.jpajsonperformancemeasurement.controller.dto;

import lombok.*;

import java.util.HashMap;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class PostVoteRatio {
    private Long postId;
    private String title;
    private String content;
    private HashMap<Boolean, Double> ratio;
}

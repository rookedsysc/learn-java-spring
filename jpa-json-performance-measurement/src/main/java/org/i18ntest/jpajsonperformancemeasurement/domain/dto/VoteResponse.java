package org.i18ntest.jpajsonperformancemeasurement.domain.dto;

import lombok.*;
import org.i18ntest.jpajsonperformancemeasurement.domain.Vote;

import java.util.HashMap;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class VoteResponse {
    private Long id;
    private Long memberId;
    private Boolean vote;

    public static VoteResponse fromEntity(Vote vote) {
        return VoteResponse.builder()
                .id(vote.getId())
                .memberId(vote.getMemberId())
                .vote(vote.getVote())
                .build();
    }
}

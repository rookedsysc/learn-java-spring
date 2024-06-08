package org.i18ntest.jpajsonperformancemeasurement.domain.dto;

public record VoteRequest(
        Long memberId,
        Boolean vote
) {
}

package org.i18ntest.jpajsonperformancemeasurement.application;

import lombok.RequiredArgsConstructor;
import org.i18ntest.jpajsonperformancemeasurement.domain.JPAPost;
import org.i18ntest.jpajsonperformancemeasurement.domain.Vote;
import org.i18ntest.jpajsonperformancemeasurement.repository.JPAPostRepository;
import org.i18ntest.jpajsonperformancemeasurement.repository.VoteRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JPAPostService {
    private final JPAPostRepository postRepository;
    private final VoteRepository voteRepository;

    public JPAPost save(JPAPost post) {
        return postRepository.save(post);
    }

    public Vote vote(Vote vote) {
        return voteRepository.save(vote);
    }
}

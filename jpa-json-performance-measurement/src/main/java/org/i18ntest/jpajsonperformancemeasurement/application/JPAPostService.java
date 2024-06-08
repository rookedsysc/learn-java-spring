package org.i18ntest.jpajsonperformancemeasurement.application;

import lombok.RequiredArgsConstructor;
import org.i18ntest.jpajsonperformancemeasurement.domain.JPAPost;
import org.i18ntest.jpajsonperformancemeasurement.domain.Vote;
import org.i18ntest.jpajsonperformancemeasurement.domain.dto.VoteRequest;
import org.i18ntest.jpajsonperformancemeasurement.repository.JPAPostRepository;
import org.i18ntest.jpajsonperformancemeasurement.repository.VoteRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JPAPostService {
    private final JPAPostRepository postRepository;
    private final VoteRepository voteRepository;

    public JPAPost savePost(JPAPost post) {
        return postRepository.save(post);
    }

    public Vote vote(Long postId, VoteRequest request) {
        JPAPost savedPost = postRepository.findById(postId)
                .orElseThrow();
        Vote vote = Vote.builder()
                .memberId(request.memberId())
                .vote(request.vote())
                .post(savedPost)
                .build();
        return voteRepository.save(vote);
    }
}

package org.i18ntest.jpajsonperformancemeasurement.application;

import lombok.RequiredArgsConstructor;
import org.i18ntest.jpajsonperformancemeasurement.controller.dto.PostResponse;
import org.i18ntest.jpajsonperformancemeasurement.domain.JPAPost;
import org.i18ntest.jpajsonperformancemeasurement.domain.Vote;
import org.i18ntest.jpajsonperformancemeasurement.domain.dto.VoteRequest;
import org.i18ntest.jpajsonperformancemeasurement.domain.dto.VoteResponse;
import org.i18ntest.jpajsonperformancemeasurement.repository.JPAPostRepository;
import org.i18ntest.jpajsonperformancemeasurement.repository.VoteRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JPAService {
    private final JPAPostRepository postRepository;
    private final VoteRepository voteRepository;

    public PostResponse savePost(JPAPost post) {
        JPAPost jpaPost = postRepository.save(post);
        PostResponse response = PostResponse.fromEntity(jpaPost);
        return response;
    }

    public VoteResponse vote(Long postId, VoteRequest request) {
        JPAPost savedPost = postRepository.findById(postId)
                .orElseThrow();

        Optional<Vote> alreadyVoted = voteRepository.findByMemberIdAndPostId(request.memberId(), postId);
        Vote vote;
        // 이미 투표한 항목이 존재할 경우 투표 변경
        if (alreadyVoted.isPresent()) {
            alreadyVoted.get()
                    .setVote(request.vote());
            vote = alreadyVoted.get();
            // 아닐 경우 새로운 투표 생성
        } else {
            vote = Vote.builder()
                    .memberId(request.memberId())
                    .vote(request.vote())
                    .post(savedPost)
                    .build();
        }
        vote = voteRepository.save(vote);

        return VoteResponse.fromEntity(vote);
    }
}

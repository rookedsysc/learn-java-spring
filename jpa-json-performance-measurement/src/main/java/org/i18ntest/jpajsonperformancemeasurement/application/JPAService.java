package org.i18ntest.jpajsonperformancemeasurement.application;

import lombok.RequiredArgsConstructor;
import org.i18ntest.jpajsonperformancemeasurement.controller.dto.PostResponse;
import org.i18ntest.jpajsonperformancemeasurement.controller.dto.PostVoteRatio;
import org.i18ntest.jpajsonperformancemeasurement.domain.JPAPost;
import org.i18ntest.jpajsonperformancemeasurement.domain.Vote;
import org.i18ntest.jpajsonperformancemeasurement.domain.dto.VoteRequest;
import org.i18ntest.jpajsonperformancemeasurement.domain.dto.VoteResponse;
import org.i18ntest.jpajsonperformancemeasurement.repository.JPAPostRepository;
import org.i18ntest.jpajsonperformancemeasurement.repository.VoteRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
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

    public PostVoteRatio calculateVoteRatio(Long postId) {
        JPAPost post = postRepository.findById(postId)
                .orElseThrow();

        // 각 투표 항목별로 rate를 저장함
        HashMap<Boolean, Double> rate = new HashMap<>();

        // 각 투표 항목별로 총 투표 수를 구함
        post.getVote()
                .stream()
                .forEach(
                        vote -> {
                            Double cost = rate.getOrDefault(vote.getVote(), 0.);
                            cost++;
                            rate.put(vote.getVote(), cost);
                        }
                );

        double totalVoteCount = post.getVote().size();

        // 각 투표 항목별로 투표 비율을 구함
        List<Boolean> voteOptions = List.of(true, false);
        voteOptions.forEach(key -> {
            Double totalCost = rate.getOrDefault(key, 0.);
            Double ratio = totalCost / totalVoteCount * 100;
            rate.put(key, ratio);
        });

        return PostVoteRatio.builder()
                .postId(postId)
                .title(post.getTitle())
                .content(post.getContent())
                .ratio(rate)
                .build();
    }
}

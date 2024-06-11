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
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

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

    public void deleteAll() {
        postRepository.deleteAll();
        voteRepository.deleteAll();
    }

    @Transactional
    public void bigQuery() {
        List<JPAPost> posts = IntStream.rangeClosed(1, 10)
                .mapToObj(i -> JPAPost.builder()
                        .title("Title " + i)
                        .content("Content " + i)
                        .build())
                .collect(Collectors.toList());
        postRepository.saveAll(posts);

        posts.forEach(this::createBigVotes);
    }

    private void createBigVotes(JPAPost post) {
        long requestCount = 10000L;
        List<Vote> votes = LongStream.rangeClosed(1, requestCount)
                .mapToObj(i -> Vote.builder()
                        .memberId(i) // 예시로 memberId를 i로 설정합니다.
                        .vote(i % 2 == 0)
                        .post(post)
                        .build())
                .collect(Collectors.toList());
        voteRepository.saveAll(votes);
    }
}

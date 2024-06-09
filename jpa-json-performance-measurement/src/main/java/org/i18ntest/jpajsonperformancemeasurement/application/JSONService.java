package org.i18ntest.jpajsonperformancemeasurement.application;

import lombok.RequiredArgsConstructor;
import org.i18ntest.jpajsonperformancemeasurement.controller.dto.PostResponse;
import org.i18ntest.jpajsonperformancemeasurement.domain.JSONPost;
import org.i18ntest.jpajsonperformancemeasurement.domain.dto.VoteRequest;
import org.i18ntest.jpajsonperformancemeasurement.domain.dto.VoteResponse;
import org.i18ntest.jpajsonperformancemeasurement.repository.JSONPostRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

@Service
@RequiredArgsConstructor
public class JSONService {
    private final JSONPostRepository repository;

    public PostResponse savePost(JSONPost post) {
        post = repository.save(post);
        return PostResponse.fromEntity(post);
    }

    public List<VoteResponse> vote(Long postId, VoteRequest voteDto) {
        JSONPost post = repository.findById(postId)
                .orElseThrow();
        ConcurrentHashMap<Long, Boolean> votes = post.getVotes();
        votes.put(voteDto.memberId(), voteDto.vote());
        post.setVotes(votes);
        post = repository.save(post);
        return post.getVotes()
                .entrySet()
                .stream()
                .map(e -> {
                    return VoteResponse.builder()
                            .memberId(e.getKey())
                            .vote(e.getValue())
                            .build();
                })
                .collect(Collectors.toList());
    }

//    public void vote(Long postId) {
//        JSONPost post = repository.findById(postId)
//                .orElseThrow();
//        int initialCapacity = (int) (10000000000L / 0.75 + 1);
//        ConcurrentHashMap<Long, Boolean> votes = new ConcurrentHashMap<>(initialCapacity, 0.75f);
//
//        LongStream.rangeClosed(1, 10000000000L)
//                .parallel()
//                .forEach(i -> {
//                    votes.put(i, (i % 2 == 0)); // 간단한 예로, 짝수는 true, 홀수는 false
//                });
//        post.setVotes(votes);
//        post = repository.save(post);
//    }
}

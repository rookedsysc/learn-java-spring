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
import java.util.stream.Collectors;

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
        HashMap<Long, Boolean> votes = post.getVotes();
        votes.put(voteDto.memberId(), voteDto.vote());
        post.setVotes(votes);
        post = repository.save(post);
        return post.getVotes().entrySet().stream()
                        .map(e -> {
                            return VoteResponse.builder()
                                    .memberId(e.getKey())
                                    .vote(e.getValue())
                                    .build();
                        })
                .collect(Collectors.toList());
    }
}

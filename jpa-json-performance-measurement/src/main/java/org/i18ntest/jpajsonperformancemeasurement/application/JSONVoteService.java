package org.i18ntest.jpajsonperformancemeasurement.application;

import lombok.RequiredArgsConstructor;
import org.i18ntest.jpajsonperformancemeasurement.domain.JSONPost;
import org.i18ntest.jpajsonperformancemeasurement.domain.dto.VoteDto;
import org.i18ntest.jpajsonperformancemeasurement.repository.JSONPostRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class JSONVoteService {
    private final JSONPostRepository repository;

    public JSONPost createPost(JSONPost post) {
        return repository.save(post);
    }

    public JSONPost doVote(Long postId, VoteDto voteDto) {
        JSONPost post = repository.findById(postId)
                .orElseThrow();
        HashMap<Long, Boolean> votes = post.getVotes();
        votes.put(voteDto.memberId(), voteDto.vote());
        post.setVotes(votes);
        return repository.save(post);
    }
}

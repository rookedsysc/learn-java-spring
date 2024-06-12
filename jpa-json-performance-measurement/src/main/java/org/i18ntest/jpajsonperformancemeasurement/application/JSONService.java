package org.i18ntest.jpajsonperformancemeasurement.application;

import lombok.RequiredArgsConstructor;
import org.i18ntest.jpajsonperformancemeasurement.controller.dto.PostRatioResponse;
import org.i18ntest.jpajsonperformancemeasurement.controller.dto.PostResponse;
import org.i18ntest.jpajsonperformancemeasurement.domain.JSONPost;
import org.i18ntest.jpajsonperformancemeasurement.domain.dto.VoteRequest;
import org.i18ntest.jpajsonperformancemeasurement.domain.dto.VoteResponse;
import org.i18ntest.jpajsonperformancemeasurement.repository.JSONPostRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
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

    @Transactional
    public void bigQuery() {
        List<JSONPost> posts = IntStream.rangeClosed(1, 10)
                .mapToObj(i -> new JSONPost(null, "Title " + i, "Content " + i, null))
                .collect(Collectors.toList());
        repository.saveAll(posts);

        posts.forEach(this::createBigVotes);
    }

    private void createBigVotes(JSONPost post) {
        long requestCount = 10000L;
        int initialCapacity = (int) (requestCount / 0.75 + 1);
        ConcurrentHashMap<Long, Boolean> votes = new ConcurrentHashMap<>(initialCapacity, 0.75f);

        LongStream.rangeClosed(1, requestCount)
                .parallel()
                .forEach(i -> {
                    votes.put(i, (i % 2 == 0));
                });
        post.setVotes(votes);
        repository.save(post);
    }

    public void deleteAll() {
        repository.deleteAll();
        ;
    }

    public List<PostResponse> all() {
        List<JSONPost> posts = repository.findAll();
        List<PostResponse> response = posts.stream()
                .map(PostResponse::fromEntity)
                .toList();
        return response;
    }

    @Transactional(readOnly = true)
    public List<PostRatioResponse> allRatio() {
        List<JSONPost> posts = repository.findAll();
        List<PostRatioResponse> response = posts.stream()
                .map(
                        post -> {
                            return PostRatioResponse.builder()
                                    .id(post.getId())
                                    .title(post.getTitle())
                                    .content(post.getContent())
                                    .votes(
                                            getVoteRatio(post)
                                    )
                                    .build();
                        }
                )
                .toList();
        return response;
    }

    private List<PostRatioResponse.VoteRatio> getVoteRatio(JSONPost post) {
        List<Boolean> voteOptions = List.of(true, false);
        final double totalCnt = post.getVotes()
                .size();

        List<PostRatioResponse.VoteRatio> voteRatios = voteOptions.stream(
                )
                .map(
                        option -> {
                            double ratio = (post.getVotes()
                                    .entrySet()
                                    .stream()
                                    .filter(
                                            vote -> vote.getValue()
                                                    .equals(option)
                                    )
                                    .count() / totalCnt) * 100;
                            return PostRatioResponse.VoteRatio.builder()
                                    .voteOption(option)
                                    .ratio(ratio)
                                    .build();
                        }
                )
                .toList();
        return voteRatios;
    }
}

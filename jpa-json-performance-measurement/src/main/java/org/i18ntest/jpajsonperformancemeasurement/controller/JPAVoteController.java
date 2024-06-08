package org.i18ntest.jpajsonperformancemeasurement.controller;

import lombok.RequiredArgsConstructor;
import org.i18ntest.jpajsonperformancemeasurement.application.JPAPostService;
import org.i18ntest.jpajsonperformancemeasurement.controller.dto.JPAPostRequest;
import org.i18ntest.jpajsonperformancemeasurement.domain.JPAPost;
import org.i18ntest.jpajsonperformancemeasurement.domain.Vote;
import org.i18ntest.jpajsonperformancemeasurement.domain.dto.VoteRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("jpa-votes")
@RequiredArgsConstructor
public class JPAVoteController {
    private final JPAPostService jpaPostService;
    @PostMapping("/posts")
    public JPAPost postSave(@RequestBody JPAPostRequest post) {
        return jpaPostService.savePost(post.toEntity());
    }

    @PostMapping("/{postId}")
    public Vote vote(
            @PathVariable(name = "postId") Long postId,
            @RequestBody VoteRequest request
    ) {
        return jpaPostService.vote(postId, request);
    }
}

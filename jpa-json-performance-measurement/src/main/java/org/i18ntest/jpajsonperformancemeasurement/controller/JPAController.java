package org.i18ntest.jpajsonperformancemeasurement.controller;

import lombok.RequiredArgsConstructor;
import org.i18ntest.jpajsonperformancemeasurement.application.JPAService;
import org.i18ntest.jpajsonperformancemeasurement.controller.dto.PostRequest;
import org.i18ntest.jpajsonperformancemeasurement.controller.dto.PostResponse;
import org.i18ntest.jpajsonperformancemeasurement.domain.dto.VoteRequest;
import org.i18ntest.jpajsonperformancemeasurement.domain.dto.VoteResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/jpa")
@RequiredArgsConstructor
public class JPAController {
    private final JPAService service;

    @PostMapping("/posts")
    public PostResponse postSave(@RequestBody PostRequest post) {
        return service.savePost(post.toEntity());
    }

    @PostMapping("/{postId}")
    public VoteResponse vote(
            @PathVariable(name = "postId") Long postId,
            @RequestBody VoteRequest request
    ) {
        return service.vote(postId, request);
    }
}

package org.i18ntest.jpajsonperformancemeasurement.controller;

import lombok.RequiredArgsConstructor;
import org.i18ntest.jpajsonperformancemeasurement.application.JSONService;
import org.i18ntest.jpajsonperformancemeasurement.controller.dto.PostRequest;
import org.i18ntest.jpajsonperformancemeasurement.controller.dto.PostResponse;
import org.i18ntest.jpajsonperformancemeasurement.domain.dto.VoteRequest;
import org.i18ntest.jpajsonperformancemeasurement.domain.dto.VoteResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/json")
@RequiredArgsConstructor
public class JSONController {
    private final JSONService service;

    @PostMapping("/posts")
    public PostResponse savePost(@RequestBody PostRequest request) {
        return service.savePost(request.toJSONEntity());
    }

    @PostMapping("/{postId}")
    public List<VoteResponse> vote(
            @PathVariable(name = "postId") Long postId,
            @RequestBody VoteRequest request
    ) {
        return service.vote(postId, request);
    }

    @GetMapping("/big-query")
    public void vote(
    ) {
        service.bigQuery();
    }

    @DeleteMapping("/all")
    public void deleteAll() {
        service.deleteAll();
    }
}

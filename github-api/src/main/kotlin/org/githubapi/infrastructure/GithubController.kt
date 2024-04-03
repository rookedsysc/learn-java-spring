package org.githubapi.infrastructure

import org.githubapi.application.GithubService
import org.githubapi.application.model.CommitDetail
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/github")
class GithubController(
        val githubService: GithubService
){
    @PostMapping("/commit")
    fun getCommit(
            @RequestParam url: String
    ) : CommitDetail {
        return t githubService.getCommit(url)
    }
}
package org.githubapi.application

import org.githubapi.application.model.CommitDetail
import org.githubapi.application.model.CommitRequest
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.client.RestClient
import org.springframework.web.client.RestClient.ResponseSpec

@Service
class GithubService(
        @Value("\${github.accessToken}")
        private val githubApiUrl: String,
) {

    fun getCommit(url: String): CommitDetail{
        val restClient: RestClient = RestClient.create()
        val request: CommitRequest
        if (url.contains("/pull/")) {
            request = getCommitRequestPr(url)
        } else {
            request = getCommitRequest(url)
        }
        val response: ResponseSpec = restClient.get().uri("https://api.github.com/repos/${request.owner}/${request.repo}/commits/${request.sha}")
                .header(
                "Authorization", "Bearer $githubApiUrl"
        ).retrieve()
        val result: CommitDetail? = response.body(CommitDetail::class.java)
        if (result != null) {
            return result
        } else {
            throw Exception("Commit not found")
        }
    }

    private fun getCommitRequest(url: String): CommitRequest {
        val owner = url.split("/")[3]
        val repo = url.split("/")[4]
        val sha = url.split("/commit/")[1].split("/")[0]
        return CommitRequest(owner, repo, sha)
    }

    private fun getCommitRequestPr(url: String): CommitRequest {
        val owner = url.split("/")[3]
        val repo = url.split("/")[4]
        val sha = url.split("/commits/")[1].split("/")[0]
        return CommitRequest(owner, repo, sha)
    }

}
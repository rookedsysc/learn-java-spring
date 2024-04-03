package org.githubapi.application.model

data class CommitRequest(
        val owner: String,
        val repo: String,
        val sha: String = ""
)

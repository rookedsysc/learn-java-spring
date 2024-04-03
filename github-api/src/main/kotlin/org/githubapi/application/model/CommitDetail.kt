package org.githubapi.application.model

data class CommitDetail(
    val url: String,
    val sha: String,
    val node_id: String,
    val html_url: String,
    val comments_url: String,
    val commit: Commit,
    val author: User,
    val committer: User,
    val parents: List<Parent>,
    val stats: Stats,
    val files: List<FileDetail>
)

data class Commit(
    val url: String,
    val author: Author,
    val committer: Author,
    val message: String,
    val tree: Tree,
    val comment_count: Int,
    val verification: Verification
)

data class Author(
    val name: String,
    val email: String,
    val date: String
)

data class Tree(
    val url: String,
    val sha: String
)

data class Verification(
    val verified: Boolean,
    val reason: String,
    val signature: Any?,
    val payload: Any?
)

data class User(
    val login: String,
    val id: Int,
    val node_id: String,
    val avatar_url: String,
    val gravatar_id: String,
    val url: String,
    val html_url: String,
    val followers_url: String,
    val following_url: String,
    val gists_url: String,
    val starred_url: String,
    val subscriptions_url: String,
    val organizations_url: String,
    val repos_url: String,
    val events_url: String,
    val received_events_url: String,
    val type: String,
    val site_admin: Boolean
)

data class Parent(
    val url: String,
    val sha: String
)

data class Stats(
    val additions: Int,
    val deletions: Int,
    val total: Int
)

data class FileDetail(
    val filename: String,
    val additions: Int,
    val deletions: Int,
    val changes: Int,
    val status: String,
    val raw_url: String,
    val blob_url: String,
    val patch: String
)

package com.example.ecabs_challenge_pedro_martins.Model

data class GithubEvent(
    val id: String,
    val type: String,
    val created_at: String,
    val actor: Actor,
    val repo: Repo
)

data class Actor(
    val login: String,
    val avatar_url: String
)

data class Repo(
    val id: Long,
    val name: String
)
package com.dj.gittrends.domain.model

internal data class Repository(
    val id: Long,
    val name: String,
    val fullName: String,
    val owner: Owner,
    val description: String,
    val stargazersCount: Long,
    val watchersCount: Long,
    val language: String? = null,
    val topics: List<String>
)
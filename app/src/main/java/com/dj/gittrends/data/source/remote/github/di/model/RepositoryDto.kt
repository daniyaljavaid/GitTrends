package com.dj.gittrends.data.source.remote.github.di.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal data class RepositoryDto(
    @Json(name = "id")
    val id: Long,

    @Json(name = "name")
    val name: String,

    @Json(name = "full_name")
    val fullName: String,

    @Json(name = "owner")
    val owner: OwnerDto,

    @Json(name = "description")
    val description: String,

    @Json(name = "stargazers_count")
    val stargazersCount: Long,

    @Json(name = "watchers_count")
    val watchersCount: Long,

    @Json(name = "language")
    val language: String? = null,

    @Json(name = "topics")
    val topics: List<String>
)
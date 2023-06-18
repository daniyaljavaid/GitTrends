package com.dj.gittrends.data.remote.github.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal data class TrendingRepositoriesResponseDto(
    @Json(name = "total_count")
    val totalCount: Long,
    @Json(name = "items")
    val items: List<RepositoryDto>
)
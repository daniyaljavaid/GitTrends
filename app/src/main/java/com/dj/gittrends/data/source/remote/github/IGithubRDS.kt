package com.dj.gittrends.data.source.remote.github

import com.dj.gittrends.common.ResultState
import com.dj.gittrends.data.source.remote.github.model.TrendingRepositoriesResponseDto
import kotlinx.coroutines.flow.Flow

internal interface IGithubRDS {
    fun fetchTrendingRepositories(): Flow<ResultState<TrendingRepositoriesResponseDto>>
}

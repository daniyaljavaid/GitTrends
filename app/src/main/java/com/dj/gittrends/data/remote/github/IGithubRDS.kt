package com.dj.gittrends.data.remote.github

import com.dj.gittrends.common.ResultState
import com.dj.gittrends.data.remote.github.model.TrendingRepositoriesResponseDto
import kotlinx.coroutines.flow.Flow

internal interface IGithubRDS {
    fun fetchTrendingRepositories(): Flow<ResultState<TrendingRepositoriesResponseDto>>
}

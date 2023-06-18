package com.dj.gittrends.data.source.remote.github

import com.dj.gittrends.common.ResultState
import com.dj.gittrends.common.safeApiCall
import com.dj.gittrends.data.source.remote.GithubService
import com.dj.gittrends.data.source.remote.github.model.TrendingRepositoriesResponseDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

internal class GithubRDSImpl(private val apiService: GithubService) :
    IGithubRDS {

    override fun fetchTrendingRepositories(): Flow<ResultState<TrendingRepositoriesResponseDto>> =
        flow {
            emit(
                safeApiCall {
                    apiService.getTrendingRepositories()
                }
            )
        }

}

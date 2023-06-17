package com.dj.gittrends.data.source.remote.github

import com.dj.gittrends.data.source.remote.github.model.TrendingRepositoriesResponseDto
import retrofit2.http.GET

internal interface GithubService {

    @GET("/search/repositories?q=language=+sort:stars")
    suspend fun getTrendingRepositories(): TrendingRepositoriesResponseDto
}
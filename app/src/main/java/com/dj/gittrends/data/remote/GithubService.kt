package com.dj.gittrends.data.remote

import com.dj.gittrends.data.remote.github.model.TrendingRepositoriesResponseDto
import retrofit2.http.GET

internal interface GithubService {

    @GET("/search/repositories?q=language=+sort:stars")
    suspend fun getTrendingRepositories(): TrendingRepositoriesResponseDto
}
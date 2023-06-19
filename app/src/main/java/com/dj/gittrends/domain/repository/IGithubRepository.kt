package com.dj.gittrends.domain.repository

import com.dj.gittrends.common.ResultState
import com.dj.gittrends.domain.model.Repository
import kotlinx.coroutines.flow.Flow

internal interface IGithubRepository {
    fun getRepositories(forceFetch: Boolean = false): Flow<ResultState<List<Repository>>>
}

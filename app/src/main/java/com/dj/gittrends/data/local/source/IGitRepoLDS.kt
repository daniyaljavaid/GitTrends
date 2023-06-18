package com.dj.gittrends.data.local.source

import com.dj.gittrends.data.local.entity.RepositoryEntity
import kotlinx.coroutines.flow.Flow

internal interface IGitRepoLDS {

    fun getRepositories(): Flow<List<RepositoryEntity>>

    suspend fun saveRepositories(repositories: List<RepositoryEntity>)

}

package com.dj.gittrends.data.source.local.source

import com.dj.gittrends.data.source.local.entity.RepositoryEntity
import kotlinx.coroutines.flow.Flow

internal interface IRepositoryLDS {

    fun getRepositories(): Flow<List<RepositoryEntity>>

    suspend fun saveRepositories(repositories: List<RepositoryEntity>)

}

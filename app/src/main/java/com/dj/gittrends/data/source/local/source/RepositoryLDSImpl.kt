package com.dj.gittrends.data.source.local.source

import com.dj.gittrends.data.source.local.dao.RepositoryDao
import com.dj.gittrends.data.source.local.entity.RepositoryEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class RepositoryLDSImpl @Inject constructor(private val dao: RepositoryDao) :
    IRepositoryLDS {

    override fun getRepositories(): Flow<List<RepositoryEntity>> = dao.getAllRepositories()
    override suspend fun saveRepositories(repositories: List<RepositoryEntity>) {
        dao.reInsertRepositories(repositories)
    }
}

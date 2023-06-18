package com.dj.gittrends.data.local.source

import com.dj.gittrends.data.local.dao.RepositoryDao
import com.dj.gittrends.data.local.entity.RepositoryEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class GitRepoLDSImpl @Inject constructor(private val dao: RepositoryDao) :
    IGitRepoLDS {

    override fun getRepositories(): Flow<List<RepositoryEntity>> = dao.getAllRepositories()
    override suspend fun saveRepositories(repositories: List<RepositoryEntity>) {
        dao.reInsertRepositories(repositories)
    }
}

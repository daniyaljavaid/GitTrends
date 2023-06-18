package com.dj.gittrends.data.source.local.source

import com.dj.gittrends.data.source.local.dao.RepositoryDao
import com.dj.gittrends.data.source.mapper.toRepository
import com.dj.gittrends.domain.model.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class RepositoryLDSImpl @Inject constructor(private val dao: RepositoryDao) :
    IRepositoryLDS {

    override fun getRepositories(): Flow<List<Repository>> = dao.getAllRepositories().map {
        it.map { it.toRepository() }
    }
}

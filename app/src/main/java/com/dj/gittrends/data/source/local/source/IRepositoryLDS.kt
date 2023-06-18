package com.dj.gittrends.data.source.local.source

import com.dj.gittrends.domain.model.Repository
import kotlinx.coroutines.flow.Flow

internal interface IRepositoryLDS {

    fun getRepositories(): Flow<List<Repository>>


}

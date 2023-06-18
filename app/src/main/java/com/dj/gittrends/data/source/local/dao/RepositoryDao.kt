package com.dj.gittrends.data.source.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.dj.gittrends.data.source.local.base.BaseDao
import com.dj.gittrends.data.source.local.entity.RepositoryEntity
import kotlinx.coroutines.flow.Flow

@Dao
internal interface RepositoryDao : BaseDao<RepositoryEntity> {

    @Query("SELECT * FROM repositoryentity")
    fun getAllRepositories(): Flow<List<RepositoryEntity>>
}
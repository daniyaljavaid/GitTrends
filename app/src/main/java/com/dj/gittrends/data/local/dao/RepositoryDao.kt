package com.dj.gittrends.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.dj.gittrends.data.local.base.BaseDao
import com.dj.gittrends.data.local.entity.RepositoryEntity
import kotlinx.coroutines.flow.Flow

@Dao
internal interface RepositoryDao : BaseDao<RepositoryEntity> {

    @Query("SELECT * FROM repositoryentity")
    fun getAllRepositories(): Flow<List<RepositoryEntity>>

    @Query("DELETE FROM repositoryentity")
    suspend fun deleteAll()

    @Transaction
    suspend fun reInsertRepositories(repositories: List<RepositoryEntity>) {
        deleteAll()
        insertAll(repositories)
    }
}
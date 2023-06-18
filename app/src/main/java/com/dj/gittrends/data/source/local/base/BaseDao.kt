package com.dj.gittrends.data.source.local.base

import androidx.room.*

interface BaseDao<T> {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(list: List<T>)
}

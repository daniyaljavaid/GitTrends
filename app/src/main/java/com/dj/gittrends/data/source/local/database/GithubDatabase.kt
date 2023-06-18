package com.dj.gittrends.data.source.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dj.gittrends.data.source.local.entity.RepositoryEntity

@Database(
    entities = [RepositoryEntity::class],
    version = 1,
    exportSchema = false
)
internal abstract class GithubDatabase : RoomDatabase()
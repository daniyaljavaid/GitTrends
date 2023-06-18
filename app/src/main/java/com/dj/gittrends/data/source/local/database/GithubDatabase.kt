package com.dj.gittrends.data.source.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.dj.gittrends.data.source.local.dao.RepositoryDao
import com.dj.gittrends.data.source.local.entity.RepositoryEntity
import com.dj.gittrends.data.source.local.typeconverter.ListConverter
import com.dj.gittrends.data.source.local.typeconverter.OwnerConverter

@Database(
    entities = [RepositoryEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(OwnerConverter::class, ListConverter::class)
internal abstract class GithubDatabase : RoomDatabase() {

    abstract fun repositoryDao(): RepositoryDao

}
package com.dj.gittrends.data.local.di

import android.content.Context
import androidx.room.Room
import com.dj.gittrends.data.local.dao.RepositoryDao
import com.dj.gittrends.data.local.database.GithubDatabase
import com.dj.gittrends.data.local.source.GitRepoLDSImpl
import com.dj.gittrends.data.local.source.IGitRepoLDS
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object LDSModule {

    @Provides
    @Singleton
    fun provideGithubDatabase(@ApplicationContext context: Context): GithubDatabase {
        return Room.databaseBuilder(
            context,
            GithubDatabase::class.java,
            GithubDatabase::class.java.simpleName
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideRepositoryDao(githubDatabase: GithubDatabase): RepositoryDao {
        return githubDatabase.repositoryDao()
    }

    @Provides
    fun provideGitRepoLDS(dao: RepositoryDao): IGitRepoLDS {
        return GitRepoLDSImpl(dao)
    }

}

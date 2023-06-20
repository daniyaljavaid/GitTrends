package com.dj.gittrends.data.repository.di

import com.dj.gittrends.data.local.source.IGitRepoLDS
import com.dj.gittrends.data.remote.github.IGithubRDS
import com.dj.gittrends.data.repository.GithubRepositoryImpl
import com.dj.gittrends.domain.repository.IGithubRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal object GithubRepositoryModule {

    @Provides
    fun provideGithubRepository(
        githubRDS: IGithubRDS,
        gitRepoLDS: IGitRepoLDS
    ): IGithubRepository {
        return GithubRepositoryImpl(githubRDS, gitRepoLDS)
    }

}
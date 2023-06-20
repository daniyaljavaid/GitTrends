package com.dj.gittrends.data.remote.di

import com.dj.gittrends.data.remote.GithubService
import com.dj.gittrends.data.remote.github.GithubRDSImpl
import com.dj.gittrends.data.remote.github.IGithubRDS
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal object RemoteDataSourceModule {

    @Provides
    fun provideGithubRDSRDS(service: GithubService): IGithubRDS {
        return GithubRDSImpl(service)
    }

}

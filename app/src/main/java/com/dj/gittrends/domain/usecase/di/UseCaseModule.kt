package com.dj.gittrends.domain.usecase.di

import com.dj.gittrends.domain.repository.IGithubRepository
import com.dj.gittrends.domain.usecase.GetRepositoriesUseCaseImpl
import com.dj.gittrends.domain.usecase.IGetRepositoriesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal object UseCaseModule {

    @Provides
    fun provideGetRepositoriesUseCase(githubRepository: IGithubRepository): IGetRepositoriesUseCase {
        return GetRepositoriesUseCaseImpl(githubRepository)
    }

}
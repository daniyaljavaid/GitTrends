package com.dj.gittrends.domain.usecase

import com.dj.gittrends.common.ResultState
import com.dj.gittrends.domain.model.Repository
import com.dj.gittrends.domain.repository.IGithubRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

internal class GetRepositoriesUseCaseImpl(private val githubRepository: IGithubRepository) :
    IGetRepositoriesUseCase {

    override fun invoke(forceFetch: Boolean): Flow<ResultState<List<Repository>>> = flow {
        githubRepository.getRepositories().collect { emit(it) }
    }
}

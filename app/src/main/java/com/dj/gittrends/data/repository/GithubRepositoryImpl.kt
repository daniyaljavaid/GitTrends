package com.dj.gittrends.data.repository

import com.dj.gittrends.common.ResultState
import com.dj.gittrends.data.local.source.IGitRepoLDS
import com.dj.gittrends.data.mapper.toRepository
import com.dj.gittrends.data.mapper.toRepositoryEntity
import com.dj.gittrends.data.remote.github.IGithubRDS
import com.dj.gittrends.domain.model.Repository
import com.dj.gittrends.domain.repository.IGithubRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

internal class GithubRepositoryImpl(
    private val githubRDS: IGithubRDS,
    private val gitRepoLDS: IGitRepoLDS
) : IGithubRepository {
    override fun getRepositories(forceFetch: Boolean): Flow<ResultState<List<Repository>>> = flow {
        val repositories = gitRepoLDS.getRepositories().first()

        if (forceFetch || repositories.isEmpty()) {
            githubRDS.fetchTrendingRepositories().collect {
                if (it is ResultState.Success) {
                    val repositoryEntities = it.data.items.map {
                        it.toRepositoryEntity()
                    }

                    gitRepoLDS.saveRepositories(repositoryEntities)

                    gitRepoLDS.getRepositories()
                        .map {
                            it.map { entity ->
                                entity.toRepository()
                            }
                        }
                        .collect {
                            emit(ResultState.Success(it))
                        }

                } else if (it is ResultState.Error) {
                    emit(it)
                }
            }
        } else {
            emit(
                ResultState.Success(
                    repositories.map {
                        it.toRepository()
                    }
                )
            )

        }

    }

}
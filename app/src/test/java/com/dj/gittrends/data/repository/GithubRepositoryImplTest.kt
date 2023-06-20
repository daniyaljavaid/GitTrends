package com.dj.gittrends.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.dj.gittrends.common.ResultState
import com.dj.gittrends.data.local.source.IGitRepoLDS
import com.dj.gittrends.data.mapper.toRepository
import com.dj.gittrends.data.mapper.toRepositoryEntity
import com.dj.gittrends.data.remote.github.IGithubRDS
import com.dj.gittrends.data.remote.github.fixtures.GithubFixtures
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.any
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
internal class GithubRepositoryImplTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var githubRDS: IGithubRDS

    @Mock
    private lateinit var gitRepoLDS: IGitRepoLDS

    private lateinit var githubRepositoryImpl: GithubRepositoryImpl

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        githubRepositoryImpl = GithubRepositoryImpl(githubRDS, gitRepoLDS)
    }

    @Test
    fun `getRepositories from network if forceFetch`() = runBlocking {
        val responseDto = GithubFixtures.trendingRepositoriesResponseDto

        whenever(githubRDS.fetchTrendingRepositories()).thenReturn(
            flow {
                emit(ResultState.Success(responseDto))
            }
        )

        whenever(gitRepoLDS.getRepositories()).thenReturn(
            flow {
                emit(listOf(GithubFixtures.repositoryEntity))
            }
        )

        githubRepositoryImpl.getRepositories(true).test {
            val result = awaitItem()
            Assert.assertTrue(result is ResultState.Success)
            assertEquals(
                responseDto.items.size,
                (result as ResultState.Success).data.size
            )

            assertEquals(
                responseDto.items[0].toRepositoryEntity().toRepository(),
                result.data[0]
            )

            verify(githubRDS, times(1)).fetchTrendingRepositories()
            verify(gitRepoLDS, times(1)).saveRepositories(responseDto.items.map {
                it.toRepositoryEntity()
            })
            verify(gitRepoLDS, times(2)).getRepositories()

            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun `getRepositories from network if no data in LDS`() = runBlocking {
        val responseDto = GithubFixtures.trendingRepositoriesResponseDto

        whenever(githubRDS.fetchTrendingRepositories()).thenReturn(
            flow {
                emit(ResultState.Success(responseDto))
            }
        )

        whenever(gitRepoLDS.getRepositories()).thenReturn(
            flow {
                emit(listOf())
            },
            flow {
                emit(listOf(GithubFixtures.repositoryEntity))
            }
        )

        githubRepositoryImpl.getRepositories(false).test {
            val result = awaitItem()
            Assert.assertTrue(result is ResultState.Success)
            assertEquals(
                responseDto.items.size,
                (result as ResultState.Success).data.size
            )

            assertEquals(
                responseDto.items[0].toRepositoryEntity().toRepository(),
                result.data[0]
            )

            verify(githubRDS, times(1)).fetchTrendingRepositories()
            verify(gitRepoLDS, times(1)).saveRepositories(responseDto.items.map {
                it.toRepositoryEntity()
            })
            verify(gitRepoLDS, times(2)).getRepositories()

            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun `getRepositories from LDS`() = runBlocking {

        whenever(gitRepoLDS.getRepositories()).thenReturn(
            flow {
                emit(listOf(GithubFixtures.repositoryEntity))
            }
        )

        githubRepositoryImpl.getRepositories(false).test {
            val result = awaitItem()
            Assert.assertTrue(result is ResultState.Success)
            assertEquals(
                1,
                (result as ResultState.Success).data.size
            )

            assertEquals(
                GithubFixtures.repositoryEntity.toRepository(),
                result.data[0]
            )

            verify(githubRDS, times(0)).fetchTrendingRepositories()
            verify(gitRepoLDS, times(0)).saveRepositories(any())
            verify(gitRepoLDS, times(1)).getRepositories()

            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun `getRepositories from network failure if forceFetch`() = runBlocking {
        val responseDto = GithubFixtures.trendingRepositoriesResponseDto

        whenever(githubRDS.fetchTrendingRepositories()).thenReturn(
            flow {
                emit(ResultState.Error())
            }
        )

        whenever(gitRepoLDS.getRepositories()).thenReturn(
            flow {
                emit(listOf())
            }
        )

        githubRepositoryImpl.getRepositories(true).test {
            val result = awaitItem()
            Assert.assertTrue(result is ResultState.Error)

            verify(githubRDS, times(1)).fetchTrendingRepositories()
            verify(gitRepoLDS, times(0)).saveRepositories(responseDto.items.map {
                it.toRepositoryEntity()
            })
            verify(gitRepoLDS, times(1)).getRepositories()

            cancelAndConsumeRemainingEvents()
        }
    }

}
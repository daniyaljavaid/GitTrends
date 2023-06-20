package com.dj.gittrends.domain.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.dj.gittrends.common.ResultState
import com.dj.gittrends.data.remote.github.fixtures.GithubFixtures
import com.dj.gittrends.domain.repository.IGithubRepository
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
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
internal class GetRepositoriesUseCaseImplTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var githubRepository: IGithubRepository

    private lateinit var getRepositoriesUseCaseImpl: GetRepositoriesUseCaseImpl

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        getRepositoriesUseCaseImpl = GetRepositoriesUseCaseImpl(githubRepository)
    }

    @Test
    fun `getRepositories success`() = runBlocking {
        val repository = GithubFixtures.repository

        whenever(githubRepository.getRepositories(false)).thenReturn(
            flow {
                emit(ResultState.Success(listOf(repository)))
            }
        )

        getRepositoriesUseCaseImpl.invoke(false).test {
            val result = awaitItem()
            Assert.assertTrue(result is ResultState.Success)
            Assert.assertEquals(repository, (result as ResultState.Success).data[0])

            verify(githubRepository).getRepositories(false)

            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun `getRepositories failure`() = runBlocking {
        whenever(githubRepository.getRepositories(false)).thenReturn(
            flow {
                emit(ResultState.Error())
            }
        )

        getRepositoriesUseCaseImpl.invoke(false).test {
            val result = awaitItem()
            Assert.assertTrue(result is ResultState.Error)

            verify(githubRepository).getRepositories(false)

            cancelAndConsumeRemainingEvents()
        }
    }

}
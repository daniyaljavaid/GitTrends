package com.dj.gittrends.ui.repositorylist.viewmodel

import app.cash.turbine.test
import com.dj.gittrends.MainDispatcherRule
import com.dj.gittrends.common.ResultState
import com.dj.gittrends.data.remote.github.fixtures.GithubFixtures
import com.dj.gittrends.domain.usecase.IGetRepositoriesUseCase
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
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
internal class RepositoryListViewModelTest {

    @get:Rule
    val dispatcherRule = MainDispatcherRule()

    @Mock
    lateinit var getRepositoriesUseCase: IGetRepositoriesUseCase

    private val repositoryListViewModel by lazy {
        RepositoryListViewModel(
            getRepositoriesUseCase = getRepositoriesUseCase
        )
    }

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun `RepositoryListUiState - GetRepositoriesSuccess`() = runTest {
        whenever(getRepositoriesUseCase.invoke()).thenReturn(
            flow {
                emit(ResultState.Success(listOf(GithubFixtures.repository)))
            }
        )

        repositoryListViewModel.repositoryListUiState.test {
            Assert.assertTrue(awaitItem() is RepositoryListUiState.Loading)

            val result = awaitItem()
            Assert.assertTrue(result is RepositoryListUiState.GetRepositoriesSuccess)
            result as RepositoryListUiState.GetRepositoriesSuccess

            Assert.assertTrue(result.data.isNotEmpty())
            Assert.assertEquals(result.data.first(), GithubFixtures.repository)

            verify(getRepositoriesUseCase).invoke()
        }
    }

    @Test
    fun `RepositoryListUiState - Error`() = runTest {
        whenever(getRepositoriesUseCase.invoke()).thenReturn(
            flow {
                emit(ResultState.Error())
            }
        )

        repositoryListViewModel.repositoryListUiState.test {
            Assert.assertTrue(awaitItem() is RepositoryListUiState.Loading)

            val result = awaitItem()
            Assert.assertTrue(result is RepositoryListUiState.Error)

            verify(getRepositoriesUseCase).invoke()
        }
    }
}
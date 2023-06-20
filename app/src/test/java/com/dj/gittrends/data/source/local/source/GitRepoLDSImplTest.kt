package com.dj.gittrends.data.source.local.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.dj.gittrends.data.local.dao.RepositoryDao
import com.dj.gittrends.data.local.source.GitRepoLDSImpl
import com.dj.gittrends.data.remote.github.fixtures.GithubFixtures
import kotlinx.coroutines.flow.flowOf
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
internal class GitRepoLDSImplTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var dao: RepositoryDao

    private lateinit var gitRepoLDSImpl: GitRepoLDSImpl

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        gitRepoLDSImpl = GitRepoLDSImpl(dao)
    }

    @Test
    fun `getRepositories test`() = runTest {
        val repository = GithubFixtures.repositoryEntity
        whenever(dao.getAllRepositories()).thenReturn(
            flowOf(
                listOf(repository)
            )
        )

        gitRepoLDSImpl.getRepositories().test {
            val result = awaitItem()
            verify(dao).getAllRepositories()
            Assert.assertTrue(result.size == 1)
            Assert.assertTrue(result[0] == repository)
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `saveRepositories test`() = runTest {
        val repositories = listOf(GithubFixtures.repositoryEntity)
        gitRepoLDSImpl.saveRepositories(repositories)
        verify(dao).reInsertRepositories(repositories)
    }
}
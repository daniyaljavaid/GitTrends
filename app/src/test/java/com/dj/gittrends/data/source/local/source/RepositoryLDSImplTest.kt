package com.dj.gittrends.data.source.local.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.dj.gittrends.data.source.local.dao.RepositoryDao
import com.dj.gittrends.data.source.mapper.toRepository
import com.dj.gittrends.data.source.remote.github.fixtures.GithubFixtures
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
internal class RepositoryLDSImplTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var dao: RepositoryDao

    private lateinit var repositoryLDSImpl: RepositoryLDSImpl

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        repositoryLDSImpl = RepositoryLDSImpl(dao)
    }

    @Test
    fun `getRepositories test`() = runTest {
        val repository = GithubFixtures.repository
        whenever(dao.getAllRepositories()).thenReturn(
            flowOf(
                listOf(repository)
            )
        )

        repositoryLDSImpl.getRepositories().test {
            val result = awaitItem()
            verify(dao).getAllRepositories()
            Assert.assertTrue(result.size == 1)
            Assert.assertTrue(result[0] == repository)
            cancelAndIgnoreRemainingEvents()
        }
    }
}
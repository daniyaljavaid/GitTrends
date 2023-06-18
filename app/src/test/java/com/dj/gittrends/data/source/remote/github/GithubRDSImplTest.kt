package com.dj.gittrends.data.source.remote.github

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.dj.gittrends.common.ResultState
import com.dj.gittrends.common.exceptions.HttpException
import com.dj.gittrends.data.remote.GithubService
import com.dj.gittrends.data.remote.github.GithubRDSImpl
import com.dj.gittrends.data.remote.github.fixtures.GithubFixtures
import junit.framework.TestCase.assertEquals
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.doThrow
import org.mockito.kotlin.whenever
import kotlinx.coroutines.test.runTest
import retrofit2.HttpException as RetrofitException

@RunWith(MockitoJUnitRunner::class)
internal class GithubRDSImplTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var apiService: GithubService

    private lateinit var githubRDSImpl: GithubRDSImpl

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        githubRDSImpl = GithubRDSImpl(apiService)
    }

    @Test
    fun `fetchTrendingRepositories success`() = runTest {
        val mockResponse = GithubFixtures.trendingRepositoriesResponseDto
        whenever(apiService.getTrendingRepositories()).thenReturn(mockResponse)

        githubRDSImpl.fetchTrendingRepositories().test {
            val result = awaitItem()
            Assert.assertTrue(result is ResultState.Success)
            assertEquals(mockResponse, (result as ResultState.Success).data)
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `fetchTrendingRepositories throws HttpException`() = runTest {
        doThrow(RetrofitException::class).`when`(apiService).getTrendingRepositories()
        githubRDSImpl.fetchTrendingRepositories().test {
            val result = awaitItem()
            Assert.assertTrue(result is ResultState.Error)
            Assert.assertTrue((result as ResultState.Error).exception is HttpException)
            cancelAndIgnoreRemainingEvents()
        }
    }

}
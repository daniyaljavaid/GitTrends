package com.dj.gittrends.data.source.local.dao

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.dj.gittrends.data.local.dao.RepositoryDao
import com.dj.gittrends.data.local.database.GithubDatabase
import com.dj.gittrends.data.remote.github.fixtures.GithubFixtures
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(AndroidJUnit4::class)
internal class RepositoryDaoTest {
    private lateinit var repositoryDao: RepositoryDao
    private lateinit var database: GithubDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(
            context, GithubDatabase::class.java
        ).build()
        repositoryDao = database.repositoryDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        database.close()
    }

    @Test
    @Throws(Exception::class)
    fun insertAllTest() = runTest {
        val repositories = listOf(GithubFixtures.repository)
        repositoryDao.insertAll(repositories)
        val results = repositoryDao.getAllRepositories().take(1).first()
        assert(results.size == 1)
    }

    @Test
    @Throws(Exception::class)
    fun insertAllWithSameIdsTest() = runTest {
        val repositories = listOf(GithubFixtures.repository, GithubFixtures.repository)
        repositoryDao.insertAll(repositories)
        val results = repositoryDao.getAllRepositories().take(1).first()
        assert(results.size == 1)
    }

    @Test
    @Throws(Exception::class)
    fun insertAllWithDifferentIdsTest() = runTest {
        val repositories = listOf(
            GithubFixtures.repository,
            GithubFixtures.repository.copy(id = 2),
            GithubFixtures.repository.copy(id = 3)
        )
        repositoryDao.insertAll(repositories)
        val results = repositoryDao.getAllRepositories().take(1).first()
        assert(results.size == 3)
    }


    @Test
    @Throws(Exception::class)
    fun deleteRepositoriesTest() = runTest {
        val repositories = listOf(
            GithubFixtures.repository,
            GithubFixtures.repository.copy(id = 2),
            GithubFixtures.repository.copy(id = 3)
        )
        repositoryDao.insertAll(repositories)

        val results = repositoryDao.getAllRepositories().take(1).first()
        assert(results.size == 3)

        repositoryDao.deleteAll()

        val resultsAfterDelete = repositoryDao.getAllRepositories().take(1).first()
        assert(resultsAfterDelete.isEmpty())

    }

    @Test
    @Throws(Exception::class)
    fun reInsertRepositoriesTest() = runTest {

        repositoryDao.insertAll(
            listOf(
                GithubFixtures.repository,
                GithubFixtures.repository.copy(id = 2)
            )
        )

        val results = repositoryDao.getAllRepositories().take(1).first()
        assert(results.size == 2)

        repositoryDao.reInsertRepositories(
            listOf(
                GithubFixtures.repository,
                GithubFixtures.repository.copy(id = 2),
                GithubFixtures.repository.copy(id = 3)
            )
        )

        val resultsAfterReInsert = repositoryDao.getAllRepositories().take(1).first()
        assert(resultsAfterReInsert.size == 3)

    }

}
package com.dj.gittrends.data.source.remote.github.fixtures

import com.dj.gittrends.data.source.remote.github.model.OwnerDto
import com.dj.gittrends.data.source.remote.github.model.RepositoryDto
import com.dj.gittrends.data.source.remote.github.model.TrendingRepositoriesResponseDto

internal object GithubFixtures {
    val ownerDto = OwnerDto(1, "dj", "djUrl", "type")

    val repositoryDto = RepositoryDto(
        1, "name", "fullname", ownerDto, "description",
        100, 100, "Python", listOf()
    )

    val trendingRepositoriesResponseDto =
        TrendingRepositoriesResponseDto(1000, listOf(repositoryDto))

}
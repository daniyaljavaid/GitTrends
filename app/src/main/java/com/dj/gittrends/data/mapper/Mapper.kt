package com.dj.gittrends.data.mapper

import com.dj.gittrends.data.local.entity.OwnerEntity
import com.dj.gittrends.data.local.entity.RepositoryEntity
import com.dj.gittrends.data.remote.github.model.OwnerDto
import com.dj.gittrends.data.remote.github.model.RepositoryDto
import com.dj.gittrends.domain.model.Owner
import com.dj.gittrends.domain.model.Repository

internal fun RepositoryDto.toRepositoryEntity() = RepositoryEntity(
    id = id,
    name = name,
    fullName = fullName,
    owner = owner.toOwnerEntity(),
    description = description,
    stargazersCount = stargazersCount,
    watchersCount = watchersCount,
    language = language,
    topics = topics,
)

internal fun OwnerDto.toOwnerEntity() = OwnerEntity(
    id = id,
    login = login,
    avatarURL = avatarURL,
    type = type
)


internal fun RepositoryEntity.toRepository() = Repository(
    id = id,
    name = name,
    fullName = fullName,
    owner = owner.toOwner(),
    description = description,
    stargazersCount = stargazersCount,
    watchersCount = watchersCount,
    language = language,
    topics = topics,
)
internal fun OwnerEntity.toOwner() = Owner(
    id = id,
    login = login,
    avatarURL = avatarURL,
    type = type
)
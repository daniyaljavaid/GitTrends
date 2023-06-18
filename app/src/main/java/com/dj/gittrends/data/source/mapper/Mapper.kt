package com.dj.gittrends.data.source.mapper

import com.dj.gittrends.data.source.local.entity.OwnerEntity
import com.dj.gittrends.data.source.local.entity.RepositoryEntity
import com.dj.gittrends.data.source.remote.github.model.OwnerDto
import com.dj.gittrends.data.source.remote.github.model.RepositoryDto

internal fun RepositoryDto.toRepositoryEntity() = RepositoryEntity(
    id = id,
    name = name,
    fullName = fullName,
    ownerEntity = owner.toOwnerEntity(),
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

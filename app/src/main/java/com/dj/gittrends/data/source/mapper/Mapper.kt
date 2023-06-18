package com.dj.gittrends.data.source.mapper

import com.dj.gittrends.data.source.local.entity.Owner
import com.dj.gittrends.data.source.local.entity.RepositoryEntity
import com.dj.gittrends.data.source.remote.github.model.OwnerDto
import com.dj.gittrends.data.source.remote.github.model.RepositoryDto

internal fun RepositoryDto.toRepositoryEntity() = RepositoryEntity(
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

internal fun OwnerDto.toOwner() = Owner(
    id = id,
    login = login,
    avatarURL = avatarURL,
    type = type
)

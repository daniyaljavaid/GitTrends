package com.dj.gittrends.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
internal data class RepositoryEntity(
    @PrimaryKey
    val id: Long,
    val name: String,
    val fullName: String,
    val owner: Owner,
    val description: String,
    val stargazersCount: Long,
    val watchersCount: Long,
    val language: String? = null,
    val topics: List<String>
)
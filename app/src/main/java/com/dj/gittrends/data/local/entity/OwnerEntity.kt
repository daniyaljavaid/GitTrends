package com.dj.gittrends.data.local.entity


internal data class OwnerEntity(
    val id: Long,
    val login: String,
    val avatarURL: String,
    val type: String
)
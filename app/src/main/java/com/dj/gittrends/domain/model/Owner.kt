package com.dj.gittrends.domain.model


internal data class Owner(
    val id: Long,
    val login: String,
    val avatarURL: String,
    val type: String
)
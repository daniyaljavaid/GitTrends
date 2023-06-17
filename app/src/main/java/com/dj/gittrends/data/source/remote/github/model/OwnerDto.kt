package com.dj.gittrends.data.source.remote.github.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal data class OwnerDto(
    @Json(name = "id")
    val id: Long,

    @Json(name = "login")
    val login: String,

    @Json(name = "avatar_url")
    val avatarURL: String,

    @Json(name = "type")
    val type: String
)
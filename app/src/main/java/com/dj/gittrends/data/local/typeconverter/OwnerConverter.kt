package com.dj.gittrends.data.local.typeconverter

import androidx.room.TypeConverter
import com.dj.gittrends.data.local.entity.OwnerEntity
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

internal class OwnerConverter {

    private val moshi: Moshi by lazy {
        Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    }

    private val ownerEntityAdapter: JsonAdapter<OwnerEntity> by lazy {
        moshi.adapter(OwnerEntity::class.java)
    }

    @TypeConverter
    fun ownerToString(ownerEntity: OwnerEntity): String {
        return ownerEntityAdapter.toJson(ownerEntity)
    }

    @TypeConverter
    fun stringToOwner(json: String): OwnerEntity? {
        return ownerEntityAdapter.fromJson(json)
    }

}

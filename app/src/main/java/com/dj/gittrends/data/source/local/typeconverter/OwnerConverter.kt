package com.dj.gittrends.data.source.local.typeconverter

import androidx.room.TypeConverter
import com.dj.gittrends.data.source.local.entity.Owner
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import javax.inject.Inject

internal class OwnerConverter {

    @Inject
    lateinit var moshi: Moshi

    private val ownerAdapter: JsonAdapter<Owner> by lazy {
        moshi.adapter(Owner::class.java)
    }

    @TypeConverter
    fun ownerToString(owner: Owner): String {
        return ownerAdapter.toJson(owner)
    }

    @TypeConverter
    fun stringToOwner(json: String): Owner? {
        return ownerAdapter.fromJson(json)
    }

}

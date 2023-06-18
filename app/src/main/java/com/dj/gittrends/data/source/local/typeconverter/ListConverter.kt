package com.dj.gittrends.data.source.local.typeconverter

import androidx.room.TypeConverter
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import java.lang.reflect.Type

internal class ListConverter {

    private val moshi: Moshi by lazy {
        Moshi.Builder().build()
    }

    private val listAdapter: JsonAdapter<List<String>> by lazy {
        val listOfString: Type = Types.newParameterizedType(
            List::class.java,
            String::class.java
        )
        val adapter: JsonAdapter<List<String>> = moshi.adapter(listOfString)
        adapter
    }

    @TypeConverter
    fun listToString(list: List<String>): String {
        return listAdapter.toJson(list)
    }

    @TypeConverter
    fun stringToList(json: String): List<String> {
        return listAdapter.fromJson(json) ?: listOf()
    }

}

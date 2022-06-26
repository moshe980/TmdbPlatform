package com.giniapps.tmdbplatform.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


class SetToStringConverter {
    @TypeConverter
    fun convertToDatabaseColumn(set: Set<*>): String {
        // Marshaling
        return Gson().toJson(set)
    }

    @TypeConverter
    fun convertToEntityAttribute(listOfString: String): Set<String> {
        // unmarshalling
        val listType: Type = object : TypeToken<Set<String>>() {}.type
        return Gson().fromJson(listOfString, listType)
    }

}
package com.giniapps.tmdbplatform.database.data

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


class ListToStringConverter {
    @TypeConverter
    fun convertToDatabaseColumn(list: List<*>): String {
        // Marshaling
        return Gson().toJson(list)
    }

    @TypeConverter
    fun convertToEntityAttribute(listOfString: String): List<String> {
        // unmarshalling
        val listType: Type = object : TypeToken<ArrayList<String>>() {}.type
        return Gson().fromJson(listOfString, listType)
    }

}
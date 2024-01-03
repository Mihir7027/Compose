package com.enrich.enrich_news.data.local

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.enrich.enrich_news.domain.model.Source


/**
 * Type converter for converting Source objects to and from String representation.
 * This converter is used by Room to persist Source objects in the database.
 */
@ProvidedTypeConverter
class NewsTypeConverter {

    /**
     * Converts a Source object to its String representation.
     *
     * @param source The Source object to be converted.
     * @return A String representing the Source object.
     */
    @TypeConverter
    fun sourceToString(source: Source): String {
        return "${source.id},${source.name},"
    }

    /**
     * Converts a String representation back to a Source object.
     *
     * @param source The String representing the Source object.
     * @return The Source object parsed from the String.
     */
    @TypeConverter
    fun stringToSource(source: String): Source {
        return source.split(",").let { sourceArray ->
            Source(id = sourceArray[0], sourceArray[1])
        }
    }
}
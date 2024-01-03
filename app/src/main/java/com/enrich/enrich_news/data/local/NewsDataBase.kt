package com.enrich.enrich_news.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.enrich.enrich_news.domain.model.Article

/**
 * Room database for managing news articles.
 */
@Database(entities = [Article::class], version = 1)
@TypeConverters(NewsTypeConverter::class)
abstract class NewsDataBase : RoomDatabase() {
    /**
     * Provides access to the NewsDao to perform database operations related to articles.
     *
     * @return An instance of NewsDao.
     */
    abstract val newsDao: NewsDao
}
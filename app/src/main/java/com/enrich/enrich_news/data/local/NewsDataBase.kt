package com.enrich.enrich_news.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.enrich.enrich_news.domain.model.Article
import com.enrich.enrich_news.domain.model.User

/**
 * Room database for managing news articles and users.
 */
@Database(entities = [Article::class, User::class], version = 2)
@TypeConverters(NewsTypeConverter::class)
abstract class NewsDataBase : RoomDatabase() {
    /**
     * Provides access to the NewsDao to perform database operations related to articles.
     *
     * @return An instance of NewsDao.
     */
    abstract val newsDao: NewsDao

    /**
     * Provides access to the UserDao to perform database operations related to users.
     *
     * @return An instance of UserDao.
     */
    abstract val userDao: UserDao
}
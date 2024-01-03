package com.enrich.enrich_news.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.enrich.enrich_news.domain.model.Article
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object (DAO) for managing news articles in the local database.
 */
@Dao
interface NewsDao {

    /**
     * Inserts or replaces an article in the database.
     *
     * @param article The article to be inserted or replaced.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(article: Article)

    /**
     * Deletes an article from the database.
     *
     * @param article The article to be deleted.
     */
    @Delete
    suspend fun delete(article: Article)

    /**
     * Retrieves all articles from the database as a Flow.
     *
     * @return A Flow emitting a list of articles.
     */
    @Query("Select * FROM Article")
    fun getArticles(): Flow<List<Article>>

    /**
     * Retrieves an article by its URL.
     *
     * @param url The URL of the article to retrieve.
     * @return The article corresponding to the given URL, or null if not found.
     */
    @Query("SELECT * FROM Article WHERE url=:url")
    suspend fun getArticleById(url: String): Article?
}
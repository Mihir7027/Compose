package com.enrich.enrich_news.domain.usecases.news

import com.enrich.enrich_news.data.local.NewsDao
import com.enrich.enrich_news.domain.model.Article
import javax.inject.Inject

/**
 * Use case class responsible for selecting an article by its URL.
 *
 * @property newsDao The NewsDao instance for performing database operations.
 */
class SelectArticleByUrl @Inject constructor(
    private val newsDao: NewsDao,
) {

    /**
     * Invokes the use case to retrieve an article by its URL.
     *
     * @param url The URL of the article to retrieve.
     * @return The article corresponding to the provided URL, or null if not found.
     */
    suspend operator fun invoke(url: String): Article? {
        return newsDao.getArticleById(url = url)
    }

}
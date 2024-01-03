package com.enrich.enrich_news.domain.usecases.news

import com.enrich.enrich_news.data.local.NewsDao
import com.enrich.enrich_news.domain.model.Article
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Use case class responsible for selecting articles.
 *
 * @property newsDao The NewsDao instance for performing database operations.
 */
class SelectArticle @Inject constructor(
    val newsDao: NewsDao,
) {
    /**
     * Invokes the use case to retrieve a flow of articles.
     *
     * @return A Flow emitting a list of articles.
     */
    operator fun invoke(): Flow<List<Article>> {
        return newsDao.getArticles()
    }
}
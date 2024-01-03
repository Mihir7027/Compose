package com.enrich.enrich_news.domain.usecases.news

import com.enrich.enrich_news.data.local.NewsDao
import com.enrich.enrich_news.domain.model.Article
import javax.inject.Inject

/**
 * Use case class responsible for inserting or updating an article.
 *
 * @property newsDao The NewsDao instance for performing database operations.
 */
class UpsertArticle @Inject constructor(
    val newsDao: NewsDao,
) {

    /**
     * Invokes the use case to insert or update the provided article.
     *
     * @param article The article to be inserted or updated.
     */
    suspend operator fun invoke(article: Article) {
        newsDao.upsert(article)
    }
}
package com.enrich.enrich_news.domain.usecases.news

import com.enrich.enrich_news.data.local.NewsDao
import com.enrich.enrich_news.domain.model.Article
import javax.inject.Inject

/**
 * Use case class responsible for deleting an article.
 *
 * @property newsDao The NewsDao instance for performing database operations.
 */
class DeleteArticle @Inject constructor(
    val newsDao: NewsDao,
) {

    /**
     * Invokes the use case to delete the provided article.
     *
     * @param article The article to be deleted.
     */
    suspend operator fun invoke(article: Article) {
        newsDao.delete(article)
    }
}
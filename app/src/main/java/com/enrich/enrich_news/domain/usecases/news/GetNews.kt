package com.enrich.enrich_news.domain.usecases.news

import androidx.paging.PagingData
import com.enrich.enrich_news.domain.model.Article
import com.enrich.enrich_news.domain.repository.RemoteRepository
import kotlinx.coroutines.flow.Flow

/**
 * Use case class responsible for fetching news articles.
 *
 * @property remoteRepository The repository providing access to remote news data.
 */
class GetNews(
    private val remoteRepository: RemoteRepository,
) {
    /**
     * Invokes the use case to fetch news articles for the provided sources.
     *
     * @param source The list of sources to query for news articles.
     * @return A Flow emitting PagingData containing the fetched articles.
     */
    operator fun invoke(source: List<String>): Flow<PagingData<Article>> {
        return remoteRepository.getNews(source)
    }
}
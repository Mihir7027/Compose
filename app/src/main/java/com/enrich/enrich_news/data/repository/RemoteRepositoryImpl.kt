package com.enrich.enrich_news.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.enrich.enrich_news.data.remote.ApiInterface
import com.enrich.enrich_news.data.remote.NewsPagingSource
import com.enrich.enrich_news.domain.model.Article
import com.enrich.enrich_news.domain.repository.RemoteRepository
import kotlinx.coroutines.flow.Flow

/**
 * Implementation of the RemoteRepository responsible for retrieving news articles remotely.
 *
 * @property apiInterface The API interface used for fetching news articles.
 */
class RemoteRepositoryImpl(
    private val apiInterface: ApiInterface,
) : RemoteRepository {

    /**
     * Retrieves a flow of paginated news articles based on the provided sources.
     *
     * @param sources The list of sources to query for news articles.
     * @return A flow of PagingData containing articles fetched from the remote data source.
     */
    override fun getNews(sources: List<String>): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                NewsPagingSource(
                    apiInterface = apiInterface,
                    sources = sources.joinToString(separator = ",")
                )
            }
        ).flow
    }
}
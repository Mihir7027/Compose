package com.enrich.enrich_news.domain.repository

import androidx.paging.PagingData
import com.enrich.enrich_news.domain.model.Article
import kotlinx.coroutines.flow.Flow

/**
 * Interface defining operations for fetching news articles remotely.
 */
interface RemoteRepository {
    fun getNews(sources: List<String>): Flow<PagingData<Article>>
}
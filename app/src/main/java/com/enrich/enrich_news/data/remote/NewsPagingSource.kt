package com.enrich.enrich_news.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.enrich.enrich_news.domain.model.Article

/**
 * PagingSource implementation for loading paginated news articles.
 *
 * @property apiInterface The API interface used for fetching news articles.
 * @property sources The sources to query for news articles.
 */
class NewsPagingSource(
    private val apiInterface: ApiInterface,
    private val sources: String,
) : PagingSource<Int, Article>() {

    private var totalCount = 0

    /**
     * Gets the refresh key for the current state.
     *
     * @param state The current PagingState.
     * @return The refresh key.
     */
    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }

    /**
     * Loads data based on the provided params.
     *
     * @param params The LoadParams specifying the load requirements.
     * @return The LoadResult containing the loaded data and next/previous keys.
     */
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        val page = params.key ?: 1
        return try {
            val response = apiInterface.getNewsList(page = page, sources = sources)
            totalCount += response.articles.size
            val article = response.articles.distinctBy { it.title }
            LoadResult.Page(
                data = article,
                nextKey = if (totalCount == response.totalResults) {
                    null
                } else {
                    page + 1
                },
                prevKey = null
            )
        } catch (e: Exception) {

            e.printStackTrace()
            LoadResult.Error(e)
        }
    }

}
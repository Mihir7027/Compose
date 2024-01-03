package com.enrich.enrich_news.data.remote

import com.enrich.enrich_news.data.remote.dto.NewsResponse
import com.enrich.enrich_news.util.Constants
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Interface defining API endpoints for fetching news.
 */
interface ApiInterface {

    /**
     * Fetches a list of news articles based on query parameters.
     *
     * @param page The page number of results to retrieve.
     * @param sources The sources to query for news articles.
     * @param sortBy The sorting order for the articles (default value is Constants.SORT_BY).
     * @param apiKey The API key for authentication (default value is Constants.API_KEY).
     * @return A NewsResponse object containing the list of articles.
     */
    @GET("everything")
    suspend fun getNewsList(
        @Query("page") page: Int,
        @Query("q") sources: String,
        @Query("sortBy") sortBy: String = Constants.SORT_BY,
        @Query("apiKey") apiKey: String = Constants.API_KEY,
    ): NewsResponse
}
package com.enrich.enrich_news.data.remote.dto


import com.enrich.enrich_news.domain.model.Article
import com.google.gson.annotations.SerializedName

/**
 * Represents the response obtained from a news API.
 *
 * @property articles List of articles received in the response.
 * @property status The status of the response.
 * @property totalResults The total number of results in the response.
 */
data class NewsResponse(
    @SerializedName("articles")
    val articles: List<Article> = listOf(),
    @SerializedName("status")
    val status: String? = "",
    @SerializedName("totalResults")
    val totalResults: Int? = 0,
)
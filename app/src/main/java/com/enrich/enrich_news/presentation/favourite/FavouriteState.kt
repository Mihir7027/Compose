package com.enrich.enrich_news.presentation.favourite

import com.enrich.enrich_news.domain.model.Article

/**
 * Represents the state of the favorite articles screen.
 */
data class FavouriteState(
    /**
     * The list of favorite articles.
     */
    val articles: List<Article> = emptyList(),
)

package com.enrich.enrich_news.presentation.details

import com.enrich.enrich_news.domain.model.Article

/**
 * Sealed class representing different events or actions related to details in the application.
 */
sealed class DetailsEvents {
    /**
     * Subclass representing an action to add or delete an article.
     *
     * @param article The article to be added or deleted.
     */
    data class UpsertDeleteArticle(val article: Article) : DetailsEvents()


    /**
     * Subclass representing a generic event to remove a side effect related to details.
     */
    object RemoveSideEffect : DetailsEvents()
}
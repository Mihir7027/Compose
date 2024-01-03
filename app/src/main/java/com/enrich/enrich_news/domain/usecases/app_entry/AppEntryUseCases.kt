package com.enrich.enrich_news.domain.usecases.app_entry

/**
 * Data class representing a collection of use cases related to app entry.
 *
 * @property readAppEntry The use case for reading app entry state.
 * @property saveAppEntry The use case for saving app entry state.
 */
data class AppEntryUseCases(
    val readAppEntry: ReadAppEntry,
    val saveAppEntry: SaveAppEntry,
)
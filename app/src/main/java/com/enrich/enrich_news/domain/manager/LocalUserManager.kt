package com.enrich.enrich_news.domain.manager

import kotlinx.coroutines.flow.Flow

/**
 * Interface defining operations for managing local user data.
 */
interface LocalUserManager {

    /**
     * Saves the user's app entry state.
     *
     * @throws Exception if there's an issue while saving the app entry.
     */
    suspend fun saveAppEntry()

    /**
     * Reads the user's app entry state.
     *
     * @return A Flow emitting a Boolean indicating the app entry state.
     * @throws Exception if there's an issue while reading the app entry.
     */
    fun readAppEntry(): Flow<Boolean>
}
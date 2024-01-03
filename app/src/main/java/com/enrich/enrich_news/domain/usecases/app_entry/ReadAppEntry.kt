package com.enrich.enrich_news.domain.usecases.app_entry

import com.enrich.enrich_news.domain.manager.LocalUserManager
import kotlinx.coroutines.flow.Flow

/**
 * Use case class responsible for reading the app entry state.
 *
 * @property localUserManager The LocalUserManager implementation for managing local user data.
 */
class ReadAppEntry(
    private val localUserManager: LocalUserManager,
) {
    /**
     * Invokes the use case to read the app entry state.
     *
     * @return A Flow emitting a Boolean indicating the app entry state.
     */
    operator fun invoke(): Flow<Boolean> {
        return localUserManager.readAppEntry()
    }
}
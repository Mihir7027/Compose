package com.enrich.enrich_news.domain.usecases.app_entry

import com.enrich.enrich_news.domain.manager.LocalUserManager

/**
 * Use case class responsible for saving the app entry state.
 *
 * @property localUserManager The LocalUserManager implementation for managing local user data.
 */
class SaveAppEntry(
    private val localUserManager: LocalUserManager,
) {
    /**
     * Invokes the use case to save the app entry state.
     */
    suspend operator fun invoke() {
        localUserManager.saveAppEntry()
    }
}
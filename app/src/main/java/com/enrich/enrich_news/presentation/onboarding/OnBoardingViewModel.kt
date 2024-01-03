package com.enrich.enrich_news.presentation.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.enrich.enrich_news.domain.usecases.app_entry.AppEntryUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel responsible for managing the onboarding flow.
 *
 * @property appEntryUseCases Use case for handling app entry logic.
 */
@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    private val appEntryUseCases: AppEntryUseCases,
) : ViewModel() {
    /**
     * Handles events related to the onboarding flow.
     *
     * @param event The event to be handled.
     */
    fun onEvent(event: OnBoardingEvents) {
        when (event) {
            is OnBoardingEvents.SaveAppEntry -> {
                saveAppEntry()
            }
        }
    }

    /**
     * Saves app entry logic asynchronously in a coroutine scope.
     */
    private fun saveAppEntry() {
        viewModelScope.launch {
            appEntryUseCases.saveAppEntry()
        }

    }
}
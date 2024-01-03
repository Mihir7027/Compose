package com.enrich.enrich_news

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.enrich.enrich_news.domain.usecases.app_entry.AppEntryUseCases
import com.enrich.enrich_news.presentation.navGraph.Route
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

/**
 * ViewModel handling the initialization and startup logic for the Enrich News application.
 *
 * @property appEntryUseCases The use case responsible for handling app entry logic.
 */
@HiltViewModel
open class MainViewModel @Inject constructor(
    private val appEntryUseCases: AppEntryUseCases,
) : ViewModel() {

    /**
     * State representing the condition for displaying the splash screen.
     */
    var splashCondition by mutableStateOf(true)
        private set

    /**
     * State representing the start destination route for the navigation graph.
     */
    var startDestination by mutableStateOf(Route.AppStartNavigation.route)
        private set

    init {
        // Read the app entry and set the appropriate start destination based on the result
        appEntryUseCases.readAppEntry().onEach { fromHomeScreen ->
            if (fromHomeScreen) {
                startDestination = Route.NewsNavigation.route
            } else {
                startDestination = Route.AppStartNavigation.route
            }

            // Introduce a delay for splash screen visibility
            delay(300)
            splashCondition = false

        }.launchIn(viewModelScope)
    }
}

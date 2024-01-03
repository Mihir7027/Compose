package com.enrich.enrich_news.presentation.favourite

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.enrich.enrich_news.domain.usecases.news.NewsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

/**
 * ViewModel responsible for managing the state of the favorite articles screen.
 */
@HiltViewModel
class FavouriteViewModel @Inject constructor(private val newsUseCases: NewsUseCases) : ViewModel() {
    /**
     * The state representing the favorite articles.
     */
    private val _state = mutableStateOf(FavouriteState())
    val state: State<FavouriteState> = _state

    /**
     * Initializes the ViewModel and fetches the favorite articles.
     */
    init {
        getArticles()
    }

    /**
     * Retrieves the favorite articles and updates the state accordingly.
     */
    private fun getArticles() {
        newsUseCases.selectArticle().onEach {
            _state.value = _state.value.copy(articles = it)
        }.launchIn(viewModelScope)
    }
}
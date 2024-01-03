package com.enrich.enrich_news.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.enrich.enrich_news.domain.usecases.news.NewsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * ViewModel responsible for managing data related to the home screen.
 * Retrieves news articles related to a specific source and caches them for efficient retrieval.
 */
@HiltViewModel
class HomeViewModel @Inject constructor(private val newsUseCases: NewsUseCases) : ViewModel() {
    /**
     * Retrieves paginated news articles related to a specific source and caches them within the scope
     * of the ViewModel.
     *
     * @return A Flow representing the paginated news articles.
     */
    val news = newsUseCases.getNews(
        // Specifies the source for retrieving news articles (e.g., "tesla").
        source = listOf("tesla")
    ).cachedIn(viewModelScope)
}
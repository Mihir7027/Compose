package com.enrich.enrich_news.presentation.details

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.enrich.enrich_news.domain.model.Article
import com.enrich.enrich_news.domain.usecases.news.DeleteArticle
import com.enrich.enrich_news.domain.usecases.news.SelectArticleByUrl
import com.enrich.enrich_news.domain.usecases.news.UpsertArticle
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel responsible for handling details-related actions in the application.
 * Uses Jetpack's ViewModel along with Hilt's @HiltViewModel for dependency injection.
 */
@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getSavedArticleUseCase: SelectArticleByUrl,
    private val deleteArticleUseCase: DeleteArticle,
    private val upsertArticleUseCase: UpsertArticle,
) : ViewModel() {

    /** Mutable state to maintain side effect information. */
    var sideEffect by mutableStateOf<String?>(null)
        private set

    /**
     * Handles various events related to article details.
     * @param event DetailsEvents describing the action to perform.
     */
    fun onEvent(event: DetailsEvents) {
        when (event) {
            is DetailsEvents.UpsertDeleteArticle -> {
                viewModelScope.launch {
                    val article = getSavedArticleUseCase(url = event.article.url)
                    if (article == null) {
                        upsertArticle(article = event.article)
                    } else {
                        deleteArticle(article = event.article)
                    }
                }
            }

            is DetailsEvents.RemoveSideEffect -> {
                sideEffect = null
            }
        }
    }

    /**
     * Deletes the given article and updates the side effect.
     * @param article Article to be deleted.
     */
    private suspend fun deleteArticle(article: Article) {
        deleteArticleUseCase(article = article)
        sideEffect = "Article removed from favourite"
    }

    /**
     * Adds or updates the given article and updates the side effect.
     * @param article Article to be added or updated.
     */
    private suspend fun upsertArticle(article: Article) {
        upsertArticleUseCase(article = article)
        sideEffect = "Article added to favourite"
    }


}
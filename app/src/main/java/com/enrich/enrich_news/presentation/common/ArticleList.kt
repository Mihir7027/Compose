package com.enrich.enrich_news.presentation.common

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.enrich.enrich_news.domain.model.Article
import com.enrich.enrich_news.domain.model.Source
import com.enrich.enrich_news.presentation.Dimen.extraSmallPadding2
import com.enrich.enrich_news.presentation.Dimen.mediumPadding12
import com.enrich.enrich_news.ui.theme.EnrichNewsTheme


/**
 * Composable function representing a list of articles.
 *
 * @param modifier The modifier for this Composable.
 * @param articles List of articles to display.
 * @param onClick The callback for handling click events on articles.
 */
@Composable
fun ArticleList(
    modifier: Modifier = Modifier,
    article: List<Article>,
    onClick: (Article) -> Unit,
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(mediumPadding12),
        contentPadding = PaddingValues(extraSmallPadding2)
    ) {
        items(count = article.size) {
            val articles = article[it]
            ArticleCard(article = articles, onClick = {
                onClick(articles)
            })
        }
    }
}

/**
 * Composable function representing a list of paged articles.
 *
 * @param modifier The modifier for this Composable.
 * @param articles LazyPagingItems of articles to display.
 * @param onClick The callback for handling click events on articles.
 */
@Composable
fun ArticleList(
    modifier: Modifier = Modifier,
    article: LazyPagingItems<Article>,
    onClick: (Article) -> Unit,
) {
    val handlePagingResult = handlePagingResult(article = article)
    if (handlePagingResult) {
        LazyColumn(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(mediumPadding12),
            contentPadding = PaddingValues(extraSmallPadding2)
        ) {
            items(count = article.itemCount) {
                article[it]?.let { it1 ->
                    ArticleCard(article = it1, onClick = {
                        onClick(it1)
                    })
                }
            }


        }
    }


}


/**
 * Function to handle the result of paging loading state and errors.
 *
 * @param articles LazyPagingItems of articles to handle.
 * @return Boolean indicating if the loading state was handled.
 */
@Composable
fun handlePagingResult(
    article: LazyPagingItems<Article>,
): Boolean {

    val loadState = article.loadState
    val error = when {
        loadState.refresh is LoadState.Error -> {
            loadState.refresh as LoadState.Error
        }

        loadState.prepend is LoadState.Error -> {
            loadState.prepend as LoadState.Error
        }

        loadState.append is LoadState.Error -> {
            loadState.append as LoadState.Error
        }

        else -> null
    }

    return when {
        loadState.refresh is LoadState.Loading -> {
            ShimmerEffect()
            false
        }

        error != null -> {
            EmptyScreen()
            false
        }

        else -> {
            true
        }
    }
}

/**
 * Placeholder function for a shimmer loading effect.
 */
@Composable
private fun ShimmerEffect() {
    Column(verticalArrangement = Arrangement.spacedBy(mediumPadding12)) {
        repeat(10) {
            ArticleShimmerEffect(
                modifier = Modifier.padding(horizontal = mediumPadding12)
            )
        }
    }
}

/**
 * Preview function for the ArticleList Composable.
 */
@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun ArticleListPreview() {
    EnrichNewsTheme {
        ArticleCard(article = Article(
            author = "",
            content = "",
            description = "",
            publishedAt = "Just Now",
            source = Source(id = "", name = "Preview News"),
            title = "Lorem ipsum dolor sit amet",
            url = "",
            urlToImage = "",
        ), onClick = {

        })
    }
}

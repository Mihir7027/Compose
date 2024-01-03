package com.enrich.enrich_news.presentation.favourite

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import com.enrich.enrich_news.R
import com.enrich.enrich_news.domain.model.Article
import com.enrich.enrich_news.presentation.Dimen.mediumPadding12
import com.enrich.enrich_news.presentation.common.ArticleList

/**
 * Composable function for rendering the screen displaying favorite articles.
 * @param state The state containing the list of favorite articles to display.
 * @param navigate The function to navigate to detailed view when an article is selected.
 */
@Composable
fun FavouriteScreen(
    state: FavouriteState,
    navigate: (Article) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .navigationBarsPadding()
            .padding(top = mediumPadding12, start = mediumPadding12, end = mediumPadding12)
    ) {
        Text(
            text = "Favourite", style = MaterialTheme.typography.displayMedium.copy(
                fontWeight = FontWeight.Bold,
                color = colorResource(id = R.color.text_title)
            )
        )

        Spacer(modifier = Modifier.height(mediumPadding12))

        ArticleList(article = state.articles, onClick = { article ->
            navigate(article)
        })
    }
}
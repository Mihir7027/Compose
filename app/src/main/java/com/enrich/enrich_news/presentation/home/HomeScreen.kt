package com.enrich.enrich_news.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.enrich.enrich_news.R
import com.enrich.enrich_news.domain.model.Article
import com.enrich.enrich_news.presentation.Dimen.mediumPadding12
import com.enrich.enrich_news.presentation.Dimen.mediumPadding6
import com.enrich.enrich_news.presentation.common.ArticleList
import com.enrich.enrich_news.presentation.common.SearchBar

/**
 * Composable function responsible for rendering the Home screen.
 *
 * @param articles LazyPagingItems of articles to display.
 * @param navigate Function to navigate to the details screen for a specific article.
 */
@Composable
fun HomeScreen(
    articles: LazyPagingItems<Article>,
    navigate: (Article) -> Unit,
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = mediumPadding12)
            .statusBarsPadding()
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_news), contentDescription = "",
            modifier = Modifier
                .width(150.dp)
                .height(30.dp)
                .padding(horizontal = mediumPadding6)
        )
        Spacer(modifier = Modifier.height(mediumPadding12))

        SearchBar(
            modifier = Modifier.padding(horizontal = mediumPadding12), text = "",
            readOnly = true,
            onValueChange = {
            }, onClick = {
            }, onSearch = {
            })
        Spacer(modifier = Modifier.height(mediumPadding12))

        Spacer(modifier = Modifier.height(mediumPadding12))

        ArticleList(article = articles,
            modifier = Modifier.padding(horizontal = mediumPadding12),
            onClick = { article ->
                navigate(article)
            })
    }
}
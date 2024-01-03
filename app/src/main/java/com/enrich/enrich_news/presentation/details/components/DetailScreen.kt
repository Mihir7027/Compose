package com.enrich.enrich_news.presentation.details.components

import android.content.Intent
import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.enrich.enrich_news.R
import com.enrich.enrich_news.domain.model.Article
import com.enrich.enrich_news.domain.model.Source
import com.enrich.enrich_news.presentation.Dimen.articleImageHeight
import com.enrich.enrich_news.presentation.Dimen.mediumPadding12
import com.enrich.enrich_news.presentation.details.DetailsEvents
import com.enrich.enrich_news.ui.theme.EnrichNewsTheme

/**
 * Composable function to display the detailed screen of an article.
 *
 * @param article The article to display details for.
 * @param events Callback for handling events related to details.
 * @param navigateUp Callback for navigating up.
 */
@Composable
fun DetailScreen(
    article: Article,
    events: (DetailsEvents) -> Unit,
    navigateUp: () -> Unit,
) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
    ) {
        DetailsTopBar(onShareClick = {
            Intent(Intent.ACTION_SEND).also {
                it.putExtra(Intent.EXTRA_TEXT, article.url)
                it.type = "text/plain"
                if (it.resolveActivity(context.packageManager) != null) {
                    context.startActivity(it)
                }
            }
        }, onFavouriteClick = {
            events(DetailsEvents.UpsertDeleteArticle(article))
        }, onBackClick = {
            navigateUp()
        })
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(
                start = mediumPadding12,
                end = mediumPadding12,
                top = mediumPadding12
            )
        ) {
            item {
                AsyncImage(
                    model = ImageRequest.Builder(context).data(article.urlToImage).build(),
                    contentDescription = "",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(articleImageHeight)
                        .clip(MaterialTheme.shapes.medium),
                    contentScale = ContentScale.Crop,
                )

                Spacer(modifier = Modifier.padding(mediumPadding12))
                Text(
                    text = article.title ?: "",
                    style = MaterialTheme.typography.displaySmall.copy(fontSize = 18.sp),
                    color = colorResource(id = R.color.text_title)
                )
                Spacer(modifier = Modifier.padding(mediumPadding12))

                Text(
                    text = article.content ?: "",
                    style = MaterialTheme.typography.bodyMedium.copy(fontSize = 16.sp),
                    color = colorResource(id = R.color.body)
                )
                Spacer(modifier = Modifier.padding(mediumPadding12))

                Text(
                    text = article.description ?: "",
                    style = MaterialTheme.typography.bodyMedium.copy(fontSize = 14.sp),
                    color = colorResource(id = R.color.body)
                )
            }
        }

    }
}

/**
 * Preview function for the DetailScreen Composable.
 */
@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun DetailScreenPreview() {
    EnrichNewsTheme {
        DetailScreen(
            article = Article(
                author = "",
                content = "Lorem ipsum dolor sit ametLorem ipsum dolor sit amet",
                description = "Lorem ipsum dolor sit ametLorem ipsum dolor sit ametLorem ipsum dolor sit ametLorem ipsum dolor sit ametLorem ipsum dolor sit ametLorem ipsum dolor sit amet Lorem ipsum dolor sit ametLorem ipsum dolor sit ametLorem ipsum dolor sit ametLorem ipsum dolor sit ametLorem ipsum dolor sit ametLorem ipsum dolor sit amet",
                publishedAt = "Just Now",
                source = Source(id = "", name = "Preview News"),
                title = "Lorem ipsum dolor sit amet",
                url = "https://www.kasandbox.org/programming-images/avatars/spunky-sam.png",
                urlToImage = "https://www.kasandbox.org/programming-images/avatars/spunky-sam.png",
            ), events = {},
            navigateUp = {}
        )
    }
}


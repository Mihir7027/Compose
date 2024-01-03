package com.enrich.enrich_news.presentation.common

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.enrich.enrich_news.R
import com.enrich.enrich_news.domain.model.Article
import com.enrich.enrich_news.domain.model.Source
import com.enrich.enrich_news.presentation.Dimen.articleCardSize
import com.enrich.enrich_news.presentation.Dimen.extraSmallPadding2
import com.enrich.enrich_news.presentation.Dimen.extraSmallPadding3
import com.enrich.enrich_news.presentation.Dimen.timeIconSize
import com.enrich.enrich_news.ui.theme.EnrichNewsTheme

/**
 * Composable function representing a card displaying information about an article.
 *
 * @param modifier The modifier for this Composable.
 * @param article The article data to display.
 * @param onClick The callback for handling click events on the article card.
 */
@Composable
fun ArticleCard(
    modifier: Modifier = Modifier,
    article: Article,
    onClick: () -> Unit,
) {
    val context = LocalContext.current
    Row(modifier = modifier.clickable {
        onClick()
    }) {
        AsyncImage(
            modifier = Modifier
                .size(articleCardSize)
                .clip(MaterialTheme.shapes.medium),
            model = ImageRequest.Builder(context).data(article.urlToImage).build(),
            contentDescription = "",
            contentScale = ContentScale.Crop
        )
        Column(
            verticalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .padding(horizontal = extraSmallPadding3)
                .height(
                    articleCardSize
                )
        ) {
            Text(
                text = article.title ?: "",
                style = MaterialTheme.typography.bodyMedium,
                color = colorResource(
                    id = R.color.text_title
                ),
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = article.description ?: "",
                    style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                    color = colorResource(
                        id = R.color.text_title
                    )
                )
                Spacer(modifier = Modifier.width(extraSmallPadding2))
                Icon(
                    painter = painterResource(id = R.drawable.ic_clock),
                    contentDescription = "",
                    modifier = Modifier.size(timeIconSize),
                    tint = colorResource(id = R.color.body)
                )
                Spacer(modifier = Modifier.width(extraSmallPadding2))
                Text(
                    text = article.publishedAt ?: "",
                    style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                    color = colorResource(
                        id = R.color.body
                    )
                )
            }
        }
    }
}


/**
 * Preview function for the ArticleCard Composable.
 */
@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun ArticleCardPreview() {
    EnrichNewsTheme {
        ArticleCard(
            article = Article(
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

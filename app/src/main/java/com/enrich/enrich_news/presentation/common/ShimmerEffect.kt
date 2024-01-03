package com.enrich.enrich_news.presentation.common

import android.content.res.Configuration
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.enrich.enrich_news.R
import com.enrich.enrich_news.presentation.Dimen
import com.enrich.enrich_news.presentation.Dimen.mediumPadding12
import com.enrich.enrich_news.ui.theme.EnrichNewsTheme

/**
 * Modifier function to add shimmer effect animation to a Composable.
 *
 * This function creates a shimmer effect animation using infinite transition.
 *
 * @return The modified Modifier with shimmer effect animation.
 */
@Composable
fun Modifier.shimmerEffect() = composed {
    val transition = rememberInfiniteTransition()
    val alpha = transition.animateFloat(
        initialValue = 0.2f,
        targetValue = 0.9f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000),
            repeatMode = RepeatMode.Reverse,
        )
    ).value
    background(color = colorResource(id = R.color.shimmer).copy(alpha = alpha))
}


/**
 * Composable function representing a shimmer effect for an article card.
 *
 * This function creates a shimmer effect for an article card using rows and columns with shimmer animations.
 *
 * @param modifier The modifier for this Composable.
 */
@Composable
fun ArticleShimmerEffect(
    modifier: Modifier = Modifier,
) {
    Row(modifier = modifier) {
        Box(
            modifier = Modifier
                .size(Dimen.articleCardSize)
                .clip(MaterialTheme.shapes.medium)
                .shimmerEffect()
        )
        Column(
            verticalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .padding(horizontal = Dimen.extraSmallPadding3)
                .height(
                    Dimen.articleCardSize
                )
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(30.dp)
                    .size(Dimen.articleCardSize)
                    .padding(horizontal = mediumPadding12)
                    .shimmerEffect()
            )

            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .height(50.dp)
                        .size(Dimen.articleCardSize)
                        .padding(horizontal = mediumPadding12)
                        .shimmerEffect()
                )

            }
        }
    }
}

/**
 * Preview function for the ArticleShimmerEffect Composable.
 */
@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun ArticleShimmerEffectPreview() {
    EnrichNewsTheme {
        ArticleShimmerEffect()

    }
}

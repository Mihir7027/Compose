package com.enrich.enrich_news.presentation.common

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.enrich.enrich_news.presentation.Dimen.IndicatorSize
import com.enrich.enrich_news.ui.theme.EnrichNewsTheme

/**
 * Composable function representing a page indicator for indicating the currently selected page
 * among multiple pages.
 *
 * @param modifier The modifier for this Composable.
 * @param pageSize The total number of pages.
 * @param selectedPage The index of the currently selected page.
 * @param selectedColor The color for the indicator of the selected page.
 * @param unSelectedColor The color for the indicator of unselected pages.
 */
@Composable
fun PageIndicator(
    modifier: Modifier = Modifier,
    pageSize: Int,
    selectedPage: Int,
    selectedColor: Color = MaterialTheme.colorScheme.primary,
    unSelectedColor: Color = Color.Gray,
) {

    Row(modifier = modifier, horizontalArrangement = Arrangement.SpaceBetween) {
        repeat(pageSize) {
            Box(
                modifier = Modifier
                    .size(IndicatorSize)
                    .clip(CircleShape)
                    .background(
                        color = if (it == selectedPage) {
                            selectedColor
                        } else {
                            unSelectedColor
                        }
                    )
            )
        }
    }
}

/**
 * Preview function for the PageIndicator Composable.
 */
@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun PageIndicatorPreview() {
    EnrichNewsTheme {
        PageIndicator(
            pageSize = 3,
            selectedPage = 1
        )
    }
}

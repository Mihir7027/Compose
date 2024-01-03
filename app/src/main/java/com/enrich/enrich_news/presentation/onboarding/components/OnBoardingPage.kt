package com.enrich.enrich_news.presentation.onboarding.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.enrich.enrich_news.R
import com.enrich.enrich_news.presentation.Dimen.mediumPadding12
import com.enrich.enrich_news.presentation.Dimen.mediumPadding30
import com.enrich.enrich_news.presentation.onboarding.PageData
import com.enrich.enrich_news.presentation.onboarding.pages
import com.enrich.enrich_news.ui.theme.EnrichNewsTheme

/**
 * Composable function to display an onboarding page with image, title, and description.
 *
 * @param modifier Modifier for styling the layout.
 * @param pageData Data for the onboarding page containing image, title, and description.
 */
@Composable
fun OnBoardingPage(
    modifier: Modifier = Modifier,
    pageData: PageData,
) {
    Column(modifier = modifier) {
        // Displaying the image for the onboarding page.
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.60f),
            painter = painterResource(id = pageData.image),
            contentDescription = "",
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(mediumPadding12))
        // Displaying the title of the onboarding page.
        Text(
            text = pageData.title,
            modifier = Modifier.padding(mediumPadding30),
            style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.Bold),
            color = colorResource(id = R.color.display_small)
        )
        // Displaying the description of the onboarding page.
        Text(
            text = pageData.description,
            modifier = Modifier.padding(mediumPadding30),
            style = MaterialTheme.typography.bodyMedium,
            color = colorResource(id = R.color.text_medium)
        )

    }

}

/**
 * Preview function to show OnBoardingPage composable in light and dark modes.
 */
@Preview(showBackground = true)
@Preview(uiMode = UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun OnBoardingPagePreview() {
    EnrichNewsTheme {
        OnBoardingPage(pageData = pages[0])
    }
}
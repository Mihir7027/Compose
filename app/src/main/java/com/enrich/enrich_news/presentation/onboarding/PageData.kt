package com.enrich.enrich_news.presentation.onboarding

import androidx.annotation.DrawableRes
import com.enrich.enrich_news.R

/**
 * Data class representing the data for individual onboarding pages.
 *
 * @property title The title of the onboarding page.
 * @property description The description or content of the onboarding page.
 * @property image The image resource for the onboarding page.
 */
data class PageData(
    val title: String = "",
    val description: String = "",
    @DrawableRes val image: Int,
)

/**
 * List of predefined onboarding pages data to display in the onboarding screen.
 */
val pages = listOf(
    PageData(
        title = "Lorem Ipsum is simply dummy",
        description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
        image = R.drawable.intro_1
    ),
    PageData(
        title = "Lorem Ipsum is simply dummy",
        description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
        image = R.drawable.intro_2
    ),
    PageData(
        title = "Lorem Ipsum is simply dummy",
        description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
        image = R.drawable.intro_3
    )
)
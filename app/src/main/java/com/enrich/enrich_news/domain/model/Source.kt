package com.enrich.enrich_news.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Parcelable entity class representing the source of an article.
 *
 * @property id The unique identifier of the source.
 * @property name The name of the source.
 */
@Parcelize
data class Source(
    val id: String?,
    val name: String?,
) : Parcelable
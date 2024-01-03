package com.enrich.enrich_news.domain.model


import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


/**
 * Parcelable entity class representing an article.
 *
 * @property author The author of the article.
 * @property content The content of the article.
 * @property description The description of the article.
 * @property publishedAt The publication date of the article.
 * @property source The source of the article.
 * @property title The title of the article.
 * @property url The URL of the article (primary key).
 * @property urlToImage The URL to the image associated with the article.
 */
@Parcelize
@Entity
data class Article(
    val author: String?,
    val content: String?,
    val description: String?,
    val publishedAt: String?,
    val source: Source,
    val title: String?,
    @PrimaryKey val url: String,
    val urlToImage: String?,
) : Parcelable
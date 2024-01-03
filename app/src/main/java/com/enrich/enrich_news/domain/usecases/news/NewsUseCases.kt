package com.enrich.enrich_news.domain.usecases.news

/**
 * Data class representing a collection of news-related use cases.
 *
 * @property getNews Use case for fetching news articles.
 * @property upsertArticle Use case for inserting or updating articles.
 * @property deleteArticle Use case for deleting articles.
 * @property selectArticle Use case for selecting an article.
 * @property selectArticleByUrl Use case for selecting an article by URL.
 */
data class NewsUseCases(
    val getNews: GetNews,
    val upsertArticle: UpsertArticle,
    val deleteArticle: DeleteArticle,
    val selectArticle: SelectArticle,
    val selectArticleByUrl: SelectArticleByUrl,
)
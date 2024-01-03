package com.enrich.enrich_news.util

/**
 * Constants used within the Enrich News application.
 */
object Constants {

    // API key for accessing the news API
    const val API_KEY = "3e0d1b09b27a4dd58237cf3cd40470c9"

    // Base URL for the news API
    const val BASE_URL = "https://newsapi.org/v2/"

    // Name of the database used for storing news data
    const val DATABASE_NAME = "news_database"

    // Key for sorting news articles by publication date
    const val SORT_BY = "publishedAt"

    // Key for storing user settings in shared preferences
    const val USER_SETTINGS = "user_settings"

    // Key for identifying app entry status in shared preferences
    const val APP_ENTRY = "app_entry"
}

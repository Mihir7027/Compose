package com.enrich.enrich_news.presentation.onboarding

/**
 * Represents events related to onboarding in the app.
 */
sealed class OnBoardingEvents {
    /**
     * Represents an event to save app entry.
     */
    object SaveAppEntry : OnBoardingEvents()
}
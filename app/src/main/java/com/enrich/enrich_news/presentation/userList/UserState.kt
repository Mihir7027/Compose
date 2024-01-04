package com.enrich.enrich_news.presentation.userList

import com.enrich.enrich_news.domain.model.User

/**
 * Represents the state of the user screen.
 */
data class UserState(
    /**
     * The list of users.
     */
    val users: List<User> = emptyList(),
)

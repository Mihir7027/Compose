package com.enrich.enrich_news.domain.usecases.user

import com.enrich.enrich_news.data.local.UserDao
import com.enrich.enrich_news.domain.model.User
import javax.inject.Inject

/**
 * Use case responsible for retrieving a user by their ID.
 *
 * @param userDao The DAO (Data Access Object) providing access to user data.
 */
class GetUsersById @Inject constructor(
    private val userDao: UserDao,
) {
    /**
     * Invokes the use case, retrieving a User object corresponding to the provided ID.
     *
     * @param id The ID of the user to retrieve.
     * @return User object corresponding to the provided ID, or null if not found.
     */
    suspend operator fun invoke(id: Int): User? {
        return userDao.getUserById(id = id)
    }
}

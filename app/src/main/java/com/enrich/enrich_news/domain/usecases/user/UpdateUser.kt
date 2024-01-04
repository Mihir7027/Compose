package com.enrich.enrich_news.domain.usecases.user

import com.enrich.enrich_news.data.local.UserDao
import com.enrich.enrich_news.domain.model.User
import javax.inject.Inject

/**
 * Use case responsible for updating user information.
 *
 * @param userDao The DAO (Data Access Object) providing access to user data.
 */
class UpdateUser @Inject constructor(
    private val userDao: UserDao,
) {
    /**
     * Invokes the use case to update user information.
     *
     * @param user The User object containing updated information to be saved.
     */
    suspend operator fun invoke(user: User) {
        userDao.updateUser(user)
    }
}

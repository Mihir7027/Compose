package com.enrich.enrich_news.domain.usecases.user

import com.enrich.enrich_news.data.local.UserDao
import com.enrich.enrich_news.domain.model.User
import javax.inject.Inject

/**
 * Use case responsible for inserting or updating user information.
 *
 * @param userDao The DAO (Data Access Object) providing access to user data.
 */
class UpsertUser @Inject constructor(
    private val userDao: UserDao,
) {
    /**
     * Invokes the use case to insert or update user information.
     *
     * @param user The User object to be inserted or updated.
     */
    suspend operator fun invoke(user: User) {
        userDao.upsert(user)
    }
}

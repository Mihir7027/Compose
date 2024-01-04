package com.enrich.enrich_news.domain.usecases.user

import com.enrich.enrich_news.data.local.UserDao
import com.enrich.enrich_news.domain.model.User
import javax.inject.Inject

class DeleteUser @Inject constructor(
    private val userDao: UserDao,
) {

    /**
     * Invokes the use case to delete the provided user.
     *
     * @param user The user to be deleted.
     */
    suspend operator fun invoke(user: User) {
        userDao.deleteUser(user)
    }
}
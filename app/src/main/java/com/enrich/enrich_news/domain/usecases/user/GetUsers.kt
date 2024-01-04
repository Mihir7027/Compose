package com.enrich.enrich_news.domain.usecases.user

import com.enrich.enrich_news.data.local.UserDao
import com.enrich.enrich_news.domain.model.User
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Use case responsible for retrieving a list of users.
 * Invoking this class will return a Flow emitting a list of User objects.
 *
 * @param userDao The DAO (Data Access Object) providing access to user data.
 */
class GetUsers @Inject constructor(
    private val userDao: UserDao,
) {
    /**
     * Invokes the use case, returning a Flow emitting a list of User objects.
     *
     * @return Flow emitting a list of User objects.
     */
    operator fun invoke(): Flow<List<User>> {
        return userDao.getUsers()
    }
}

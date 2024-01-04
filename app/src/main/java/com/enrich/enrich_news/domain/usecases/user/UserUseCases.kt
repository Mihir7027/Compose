package com.enrich.enrich_news.domain.usecases.user

/**
 * Data class representing a collection of user-related use case instances.
 *
 * @property upsertUser The use case responsible for inserting user information.
 * @property getUsers The use case responsible for retrieving a list of users.
 * @property getUsersById The use case responsible for retrieving a user by their ID.
 * @property deleteUser The use case responsible for deleting a user.
 * @property updateUser The use case responsible for updating user information.
 */
data class UserUseCases(
    val upsertUser: UpsertUser,
    val getUsers: GetUsers,
    val getUsersById: GetUsersById,
    val deleteUser: DeleteUser,
    val updateUser: UpdateUser,
)

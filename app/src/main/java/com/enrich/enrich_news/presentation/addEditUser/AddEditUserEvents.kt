package com.enrich.enrich_news.presentation.addEditUser

import com.enrich.enrich_news.domain.model.User

/**
 * Sealed class defining different events for adding/editing users.
 */
sealed class AddEditUserEvents {

    /**
     * Event triggered to update an existing user's information.
     *
     * @property user The updated user object.
     */
    data class UpdateUser(val user: User) : AddEditUserEvents()

    /**
     * Event triggered to delete a user.
     *
     * @property user The user object to be deleted.
     */
    data class DeleteUser(val user: User) : AddEditUserEvents()

    /**
     * Event triggered to insert or update a user depending on the existence of the user object.
     *
     * @property user The user object to be inserted or updated.
     */
    data class UpsertUser(val user: User) : AddEditUserEvents()

    /**
     * Event triggered to handle validation messages.
     *
     * @property message The validation message to be handled.
     */
    data class ValidationMessage(val message: String) : AddEditUserEvents()

    /**
     * Event triggered to remove side effects.
     */
    object RemoveSideEffect : AddEditUserEvents()

    /**
     * Event triggered to remove validation effects.
     */
    object RemoveValidationEffect : AddEditUserEvents()
}

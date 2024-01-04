package com.enrich.enrich_news.presentation.addEditUser

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.enrich.enrich_news.domain.model.User
import com.enrich.enrich_news.domain.usecases.user.DeleteUser
import com.enrich.enrich_news.domain.usecases.user.UpdateUser
import com.enrich.enrich_news.domain.usecases.user.UpsertUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel responsible for managing user-related actions for adding or editing users.
 * Handles interactions with use cases for deleting, updating, or inserting users.
 *
 * @param deleteUserUseCase The use case responsible for deleting users.
 * @param upsertUserUseCase The use case responsible for inserting or updating users.
 * @param updateUserUseCase The use case responsible for updating user details.
 */
@HiltViewModel
class AddEditUserViewModel @Inject constructor(
    private val deleteUserUseCase: DeleteUser,
    private val upsertUserUseCase: UpsertUser,
    private val updateUserUseCase: UpdateUser,
) : ViewModel() {

    var sideEffect by mutableStateOf<String?>(null)
        private set
    var errorEffect by mutableStateOf<String?>(null)
        private set

    fun onEvent(event: AddEditUserEvents) {
        when (event) {
            is AddEditUserEvents.UpsertUser -> {
                viewModelScope.launch {
                    upsertUser(user = event.user)
                }
            }

            is AddEditUserEvents.DeleteUser -> {
                viewModelScope.launch {
                    deleteUser(user = event.user)
                }
            }

            is AddEditUserEvents.ValidationMessage -> {
                errorEffect = event.message
            }

            is AddEditUserEvents.RemoveSideEffect -> {
                sideEffect = null
            }

            is AddEditUserEvents.RemoveValidationEffect -> {
                errorEffect = null
            }

            is AddEditUserEvents.UpdateUser -> {
                viewModelScope.launch {
                    updateUser(user = event.user)
                }
            }
        }
    }

    private suspend fun deleteUser(user: User) {
        deleteUserUseCase(user = user)
        sideEffect = "User Removed"
    }

    private suspend fun upsertUser(user: User) {
        upsertUserUseCase(user = user)
        sideEffect = "User added"
    }

    private suspend fun updateUser(user: User) {
        updateUserUseCase(user = user)
        sideEffect = "User detail updated"

    }
}
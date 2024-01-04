package com.enrich.enrich_news.presentation.userList

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.enrich.enrich_news.domain.usecases.user.UserUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

/**
 * ViewModel responsible for managing user-related data and interactions.
 *
 * @property userUseCases Collection of user-related use cases for data manipulation.
 */
@HiltViewModel
class UserViewModel @Inject constructor(private val userUseCases: UserUseCases) : ViewModel() {

    private val _state = mutableStateOf(UserState())
    val state: State<UserState> = _state

    init {
        getUsers()
    }

    private fun getUsers() {
        userUseCases.getUsers().onEach {
            _state.value = _state.value.copy(users = it.reversed())
        }.launchIn(viewModelScope)
    }
}
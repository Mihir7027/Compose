package com.enrich.enrich_news.presentation.userList

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.enrich.enrich_news.R
import com.enrich.enrich_news.domain.model.User
import com.enrich.enrich_news.presentation.Dimen
import com.enrich.enrich_news.presentation.userList.component.UserList

/**
 * Composable function representing a screen displaying a list of users.
 *
 * @param state The current state containing user information to display.
 * @param navigateToUserDetail Callback function triggered to navigate to user details.
 * @param navigateToAddUser Callback function triggered to navigate to add a new user.
 */
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserListScreen(
    state: UserState,
    navigateToUserDetail: (User) -> Unit,
    navigateToAddUser: () -> Unit,
) {
    Scaffold(topBar = { },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navigateToAddUser()
                }
            ) {
                Icon(Icons.Filled.Add, "")
            }
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .statusBarsPadding()
                    .padding(
                        top = Dimen.mediumPadding12,
                        start = Dimen.mediumPadding12,
                        end = Dimen.mediumPadding12
                    )
            ) {
                Text(
                    text = "Users",
                    style = MaterialTheme.typography.displayMedium.copy(
                        fontWeight = FontWeight.Bold,
                        color = colorResource(id = R.color.text_title)
                    )
                )

                Spacer(modifier = Modifier.height(Dimen.mediumPadding12))

                if (state.users.isEmpty()) {
                    // Show a message when there are no users
                    Text(
                        modifier = Modifier
                            .fillMaxSize()
                            .wrapContentSize(Alignment.Center),
                        text = "No users available",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurface // Change color as needed
                    )
                } else {
                    // Show the list of users
                    UserList(user = state.users, onClick = { user ->
                        navigateToUserDetail(user)
                    })
                }

            }
        })
}


@Preview
@Composable
fun UserListScreenPreview() {
    val sampleUserState = UserState(
        users = listOf(
            User(1, "John", "Doe", 1, "john@example.com"),
            User(2, "Jane", "Smith", 2, "jane@example.com"),
        )
    )
    UserListScreen(
        state = sampleUserState,
        navigateToAddUser = {},
        navigateToUserDetail = {}
    )
}

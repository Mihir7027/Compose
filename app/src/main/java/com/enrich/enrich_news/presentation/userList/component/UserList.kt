package com.enrich.enrich_news.presentation.userList.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.enrich.enrich_news.domain.model.User
import com.enrich.enrich_news.presentation.Dimen

/**
 * Composable function representing a list of users.
 *
 * @param modifier The modifier for styling and layout configuration.
 * @param user The list of users to be displayed.
 * @param onClick Callback function triggered when a user item is clicked.
 */
@Composable
fun UserList(
    modifier: Modifier = Modifier,
    user: List<User>,
    onClick: (User) -> Unit,
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(Dimen.mediumPadding12),
        contentPadding = PaddingValues(Dimen.extraSmallPadding2)
    ) {
        items(count = user.size) {
            UserListItem(user = user[it], onClick = {
                onClick(user[it])
            })
        }
    }
}

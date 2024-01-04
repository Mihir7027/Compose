package com.enrich.enrich_news.presentation.userList.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.enrich.enrich_news.domain.model.User

/**
 * Composable function representing a single item displaying user information.
 *
 * @param user The user object containing information to display.
 * @param onClick Callback function triggered when this item is clicked.
 */
@Composable
fun UserListItem(user: User, onClick: () -> Unit) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable(onClick = onClick),
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Name: ${user.firstName} ${user.lastName}",
                style = TextStyle(fontWeight = FontWeight.Bold)
            )
            Spacer(modifier = Modifier.height(4.dp))
            val gender = when (user.gender) {
                1 -> "Male"
                2 -> "Female"
                else -> "Unknown"
            }
            Text(text = "Gender: $gender")
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "Email: ${user.emailAddress}")
        }
    }
}

@Preview
@Composable
fun UserListItemPreview() {
    val user = User(
        id = 1,
        firstName = "John",
        lastName = "Doe",
        gender = 1,
        emailAddress = "johndoe@example.com"
    )

    UserListItem(user = user, onClick = {})
}


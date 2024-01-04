package com.enrich.enrich_news.presentation.addEditUser.components

import android.content.res.Configuration
import android.util.Patterns.EMAIL_ADDRESS
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.enrich.enrich_news.R
import com.enrich.enrich_news.domain.model.User
import com.enrich.enrich_news.presentation.Dimen.mediumPadding12
import com.enrich.enrich_news.presentation.Dimen.mediumPadding6
import com.enrich.enrich_news.presentation.addEditUser.AddEditUserEvents
import com.enrich.enrich_news.ui.theme.EnrichNewsTheme

/**
 * Composable function representing the Add/Edit User screen.
 * Allows users to input or edit user details like first name, last name, email, and gender.
 *
 * @param user The user data being edited. Defaults to an empty User object.
 * @param events Callback to pass user events such as update, delete, validation messages, etc.
 * @param navigateUp Callback to navigate up or go back.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditUserScreen(
    user: User = User(),
    events: (AddEditUserEvents) -> Unit,
    navigateUp: () -> Unit,
) {

    var firstName by remember { mutableStateOf(user.firstName ?: "") }
    var lastName by remember { mutableStateOf(user.lastName ?: "") }
    var email by remember { mutableStateOf(user.emailAddress ?: "") }
    var selectedGender by remember { mutableIntStateOf(user.gender ?: 1) } // Default to 1 (male)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
    ) {
        AddEditUserTopBar(onBackClick = {
            navigateUp()
        })

        TextField(modifier = Modifier
            .fillMaxWidth()
            .padding(mediumPadding12),
            value = firstName,
            onValueChange = {
                firstName = it
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Text
            ),
            singleLine = true,
            label = { Text(stringResource(id = R.string.first_name)) })

        TextField(modifier = Modifier
            .fillMaxWidth()
            .padding(mediumPadding12),
            value = lastName,
            onValueChange = {
                lastName = it
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Text
            ),
            label = { Text(stringResource(id = R.string.last_name)) })

        TextField(modifier = Modifier
            .fillMaxWidth()
            .padding(mediumPadding12),
            value = email,
            onValueChange = {
                email = it
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Email
            ),
            label = { Text(stringResource(id = R.string.email)) })

        Row(
            modifier = Modifier.padding(mediumPadding12),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                stringResource(
                    id = R.string.gender
                )
            )
            Spacer(
                modifier = Modifier.width(mediumPadding12)
            )

            RadioButton(
                selected = selectedGender == 1,
                onClick = {
                    selectedGender = 1
                }
            )

            Text(
                text = stringResource(
                    id = R.string.male
                )
            )

            Spacer(
                modifier = Modifier.width(mediumPadding6)
            )

            RadioButton(
                selected = selectedGender == 2,
                onClick = {
                    selectedGender = 2
                }
            )

            Text(
                stringResource(
                    id = R.string.female
                )
            )
        }

        val isNewUser = user.id == 0
        Row(
            modifier = Modifier.padding(mediumPadding12),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = {
                    val errorMessage = isFormValid(
                        email = email,
                        firstName = firstName,
                        lastName = lastName,
                        gender = selectedGender
                    )
                    if (errorMessage.isEmpty()) {
                        val updatedUser = User(
                            id = user.id,
                            firstName = firstName,
                            lastName = lastName,
                            emailAddress = email,
                            gender = selectedGender
                        )
                        if (isNewUser) {
                            events(AddEditUserEvents.UpsertUser(updatedUser))
                        } else {
                            events(AddEditUserEvents.UpdateUser(updatedUser))
                        }
                    } else {
                        events(AddEditUserEvents.ValidationMessage(errorMessage))
                    }

                }, modifier = Modifier.padding(end = mediumPadding12)
            ) {
                if (isNewUser) {
                    Text(stringResource(id = R.string.add))
                } else {
                    Text(stringResource(id = R.string.update))
                }
            }

            if (!isNewUser) {
                Button(
                    onClick = {
                        events(AddEditUserEvents.DeleteUser(user))
                    }, modifier = Modifier.padding(end = mediumPadding12)
                ) {
                    Text(stringResource(id = R.string.delete))
                }
            }
        }


        // Button to submit the form

    }
}


private fun isFormValid(
    email: String,
    firstName: String,
    lastName: String,
    gender: Int,
): String {
    var message = ""
    if (firstName.isEmpty()) {
        message = "Enter first name"
    } else if (lastName.isEmpty()) {
        message = "Enter last name"
    } else if (email.isEmpty() || !EMAIL_ADDRESS.matcher(email).matches()) {
        message = "Enter valid email address"
    } else if (gender == -1) {
        message = "Select gender"
    }
    return message
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun AddEditUserScreenPreview() {
    EnrichNewsTheme {
        AddEditUserScreen(events = {}, navigateUp = {})
    }
}


package com.enrich.enrich_news.presentation.addEditUser.components

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.enrich.enrich_news.R
import com.enrich.enrich_news.ui.theme.EnrichNewsTheme

/**
 * Composable function representing the top app bar for the Add/Edit User screen.
 *
 * @param onBackClick Callback function triggered when the back button is clicked.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditUserTopBar(
    onBackClick: () -> Unit,
) {

    TopAppBar(title = {},
        modifier = Modifier.fillMaxWidth(),
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = Color.Transparent,
            actionIconContentColor = colorResource(id = R.color.body),
            navigationIconContentColor = colorResource(id = R.color.body)
        ),
        navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(painter = painterResource(id = R.drawable.ic_back), contentDescription = "")
            }
        })
}

/**
 * Preview function for the DetailsTopBar Composable.
 */
@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun AddEditUserTopBarPreview() {
    EnrichNewsTheme {
        Box(modifier = Modifier.background(MaterialTheme.colorScheme.background)) {
            AddEditUserTopBar({})
        }
    }
}


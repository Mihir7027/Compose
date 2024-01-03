package com.enrich.enrich_news.presentation.details.components

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
 * Composable function that displays the top app bar for the details screen.
 * Provides options to navigate back, share, and add to favorites.
 *
 * @param onShareClick     Callback for the share action click.
 * @param onFavouriteClick Callback for the favorite action click.
 * @param onBackClick      Callback for the back navigation click.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsTopBar(
    onShareClick: () -> Unit,
    onFavouriteClick: () -> Unit,
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
        },
        actions = {
            IconButton(onClick = onFavouriteClick) {
                Icon(painter = painterResource(id = R.drawable.ic_like), contentDescription = "")
            }

            IconButton(onClick = onShareClick) {
                Icon(painter = painterResource(id = R.drawable.ic_share), contentDescription = "")
            }
        })
}

/**
 * Preview function for the DetailsTopBar Composable.
 */
@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun DetailsTopBarPreview() {
    EnrichNewsTheme {
        Box(modifier = Modifier.background(MaterialTheme.colorScheme.background)) {
            DetailsTopBar({}, {}, {})
        }
    }
}


package com.enrich.enrich_news.presentation.common

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.enrich.enrich_news.ui.theme.EnrichNewsTheme
import com.enrich.enrich_news.ui.theme.WhiteGray


/**
 * Composable function representing a custom Button with specific styling for news-related actions.
 *
 * @param text The text to be displayed on the button.
 * @param onClick The callback for handling click events on the button.
 */
@Composable
fun NewsButton(
    text: String,
    onClick: () -> Unit,
) {

    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = Color.White
        ),
        shape = RoundedCornerShape(size = 6.dp)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.SemiBold)
        )
    }
}

/**
 * Composable function representing a custom TextButton with specific styling for news-related actions.
 *
 * @param text The text to be displayed on the TextButton.
 * @param onClick The callback for handling click events on the TextButton.
 */
@Composable
fun NewsTextButton(
    text: String,
    onClick: () -> Unit,
) {

    TextButton(
        onClick = onClick,
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.SemiBold),
            color = WhiteGray
        )
    }
}

/**
 * Preview function for the NewsButton and NewsTextButton Composables.
 */
@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun NewsButtonPreview() {
    EnrichNewsTheme {
        Column() {
            NewsButton("Next", onClick = {})
            NewsTextButton("Next", onClick = {})
        }

    }
}

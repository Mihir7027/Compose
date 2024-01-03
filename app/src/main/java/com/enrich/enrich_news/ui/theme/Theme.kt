package com.enrich.enrich_news.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

// Define colors for dark theme
private val DarkColorScheme = darkColorScheme(
    background = Black,
    primary = Blue,
    error = DarkRed,
    surface = LightBlack
)

// Define colors for light theme
private val LightColorScheme = lightColorScheme(
    background = Color.White,
    primary = Blue,
    error = LightRed,
    surface = Color.White
)

/**
 * Custom theme setup for the Enrich News app, adapting to system dark/light mode.
 *
 * @param darkTheme Whether the app should use the system's dark mode.
 * @param dynamicColor If true and supported (Android 12+), the theme will adapt dynamically based on system theme changes.
 * @param content The content to display within this theme.
 */
@Composable
fun EnrichNewsTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit,
) {
    // Determine the color scheme based on the theme mode and system compatibility
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    val view = LocalView.current
    // Apply side effect to update status bar color and appearance
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    // Apply the MaterialTheme with the selected color scheme and typography
    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}

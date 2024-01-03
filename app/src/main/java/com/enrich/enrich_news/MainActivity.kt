package com.enrich.enrich_news

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import com.enrich.enrich_news.presentation.navGraph.NavGraph
import com.enrich.enrich_news.ui.theme.EnrichNewsTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint

/**
 * The main activity of the Enrich News application.
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    // Access the MainViewModel using viewModels delegate from Jetpack Compose
    val viewModel by viewModels<MainViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Set system windows to extend to display cutouts and other non-system areas
        WindowCompat.setDecorFitsSystemWindows(window, false)

        // Install a splash screen that stays until the splashCondition in the ViewModel is false
        installSplashScreen().apply {
            setKeepOnScreenCondition {
                viewModel.splashCondition
            }
        }
        setContent {
            // Determine if the system is in dark mode
            val isDarkModeEnabled = isSystemInDarkTheme()
            val sysController = rememberSystemUiController()

            // Set system bars color and icons based on the current dark mode status
            SideEffect {
                sysController.setSystemBarsColor(
                    color = Color.Transparent,
                    darkIcons = !isDarkModeEnabled
                )
            }
            // Set the EnrichNewsTheme for the entire app
            EnrichNewsTheme {
                Box(modifier = Modifier.background(color = MaterialTheme.colorScheme.background)) {
                    NavGraph(startDestination = viewModel.startDestination)
                }
            }
        }
    }
}
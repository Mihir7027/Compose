package com.enrich.enrich_news.presentation.navigator

import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.enrich.enrich_news.R
import com.enrich.enrich_news.domain.model.Article
import com.enrich.enrich_news.presentation.details.DetailViewModel
import com.enrich.enrich_news.presentation.details.DetailsEvents
import com.enrich.enrich_news.presentation.details.components.DetailScreen
import com.enrich.enrich_news.presentation.favourite.FavouriteScreen
import com.enrich.enrich_news.presentation.favourite.FavouriteViewModel
import com.enrich.enrich_news.presentation.home.HomeScreen
import com.enrich.enrich_news.presentation.home.HomeViewModel
import com.enrich.enrich_news.presentation.navGraph.Route
import com.enrich.enrich_news.presentation.navigator.components.BottomNavigation
import com.enrich.enrich_news.presentation.navigator.components.BottomNavigationItem

/**
 * Composable function representing the navigation logic for the app.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsNavigator() {

    val textHome = stringResource(id = R.string.home)
    val textFavourite = stringResource(id = R.string.favourite)
    val bottomNavigationItems = remember {
        listOf(
            BottomNavigationItem(
                icon = R.drawable.ic_home, text = textHome
            ),
            BottomNavigationItem(
                icon = R.drawable.ic_like, text = textFavourite
            ),
        )
    }

    val navController = rememberNavController()

    val backstackState = navController.currentBackStackEntryAsState().value
    var selectedItem by rememberSaveable {
        mutableIntStateOf(0)
    }

    selectedItem = when (backstackState?.destination?.route) {
        Route.HomeScreen.route -> 0
        Route.FavouriteScreen.route -> 1
        else -> 0
    }

    val isBottomBarVisible = remember(key1 = backstackState) {
        backstackState?.destination?.route == Route.HomeScreen.route ||
                backstackState?.destination?.route == Route.FavouriteScreen.route
    }
    // Scaffold composable with bottom navigation and NavHost for navigation logic...
    Scaffold(modifier = Modifier.fillMaxSize(), bottomBar = {
        if (isBottomBarVisible) {
            BottomNavigation(
                items = bottomNavigationItems,
                selected = selectedItem,
                onItemClick = { index ->
                    when (index) {
                        0 -> navigateToTab(
                            navController = navController,
                            route = Route.HomeScreen.route
                        )

                        1 -> navigateToTab(
                            navController = navController,
                            route = Route.FavouriteScreen.route
                        )
                    }
                }
            )
        }
    }) {
        val bottomPadding = it.calculateBottomPadding()
        NavHost(
            navController = navController,
            startDestination = Route.HomeScreen.route,
            modifier = Modifier.padding(bottom = bottomPadding)
        ) {
            composable(route = Route.HomeScreen.route) { backStackEntry ->
                val viewModel: HomeViewModel = hiltViewModel()
                val articles = viewModel.news.collectAsLazyPagingItems()
                HomeScreen(
                    articles = articles,
                    navigate = { article ->
                        navigateToDetail(
                            navController = navController,
                            article = article
                        )
                    }
                )
            }

            composable(route = Route.DetailScreen.route) {
                val viewModel: DetailViewModel = hiltViewModel()
                if (viewModel.sideEffect != null) {
                    Toast.makeText(LocalContext.current, viewModel.sideEffect, Toast.LENGTH_LONG)
                        .show()
                    viewModel.onEvent(DetailsEvents.RemoveSideEffect)
                }
                navController.previousBackStackEntry?.savedStateHandle?.get<Article?>("article")
                    ?.let { article ->
                        DetailScreen(
                            article = article,
                            events = viewModel::onEvent,
                            navigateUp = {
                                navController.navigateUp()
                            })
                    }
            }

            composable(route = Route.FavouriteScreen.route) {
                val viewModel: FavouriteViewModel = hiltViewModel()
                val state = viewModel.state.value
                FavouriteScreen(state = state, navigate = { article ->
                    navigateToDetail(navController = navController, article = article)
                })

            }

        }
    }

}


// Helper function to navigate to the DetailScreen and pass article data...
private fun navigateToDetail(navController: NavController, article: Article) {
    navController.currentBackStackEntry?.savedStateHandle?.set("article", article)
    navController.navigate(route = Route.DetailScreen.route) {
        navController.graph.startDestinationRoute?.let { homeScreen ->
            popUpTo(homeScreen) {
                saveState = true
            }
            restoreState = true
            launchSingleTop = true
        }
    }

}

// Helper function to navigate to a specific tab in the bottom navigation...
private fun navigateToTab(navController: NavController, route: String) {
    navController.navigate(route) {
        navController.graph.startDestinationRoute?.let { homeScreen ->
            popUpTo(homeScreen) {
                saveState = true
            }
            restoreState = true
            launchSingleTop = true
        }
    }

}
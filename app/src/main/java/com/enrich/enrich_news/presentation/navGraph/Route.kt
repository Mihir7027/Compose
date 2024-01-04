package com.enrich.enrich_news.presentation.navGraph


/**
 * Sealed class defining routes for the app's navigation.
 *
 * @property route The string representation of the route.
 */
sealed class Route(
    val route: String,
) {
    /**
     * Route to the OnBoardingScreen.
     */
    object OnBoardingScreen : Route(route = "onBoardingScreen")

    /**
     * Route to the HomeScreen.
     */
    object HomeScreen : Route(route = "homeScreen")

    /**
     * Route to the FavouriteScreen.
     */
    object FavouriteScreen : Route(route = "favouriteScreen")

    /**
     * Route to the DetailScreen.
     */
    object DetailScreen : Route(route = "detailScreen")


    /**
     * Route for the app's start navigation.
     */
    object AppStartNavigation : Route(route = "appStartNavigation")

    /**
     * Route for the news navigation.
     */
    object NewsNavigation : Route(route = "newsNavigation")

    /**
     * Route to the NewsNavigatorScreen.
     */
    object NewsNavigatorScreen : Route(route = "NewsNavigator")

    /**
     * Route to the User list screen.
     */
    object UserListScreen : Route(route = "userListScreen")


    /**
     * Route to the Add/Edit user screen.
     */
    object AddUserScreen : Route(route = "addUserScreen")


}

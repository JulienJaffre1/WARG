package com.example.warg.domain.components

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavArgument
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.warg.domain.views.WargAuthentificationScreen
import com.example.warg.domain.views.WargInscriptionScreen
import com.example.warg.domain.views.WargLibraryScreen
import com.example.warg.domain.views.WargSettingsScreen

@ExperimentalAnimationApi
@ExperimentalComposeUiApi
@Composable
fun SetupNavGraph(navHostController: NavHostController) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.WargAuthentification.route
    ) {
        composable(
            route = Screen.WargAuthentification.route
        ) {
            WargAuthentificationScreen(
                navHostController = navHostController
            )
        }
        composable(
            route = Screen.WargLibrary.route,
            arguments = listOf(
                navArgument("token") {type = NavType.StringType}
            )
        ) {
            val token = it.arguments?.getString("token")
            WargLibraryScreen(
                navHostController = navHostController,
                token = token!!
            )
        }
        composable(
            route = Screen.WargSettings.route
        ) {
            WargSettingsScreen(
                navHostController = navHostController
            )
        }
        composable(
            route = Screen.WargInscription.route
        ) {
            WargInscriptionScreen(
                navHostController = navHostController
            )
        }
    }
}

sealed class Screen(val route: String) {
    object WargAuthentification : Screen("WargAuthentificationScreen")
    object WargLibrary : Screen("WargLibraryScreen/{token}")
    object WargSettings : Screen("WargSettings")
    object WargInscription : Screen("WargInscription")
}
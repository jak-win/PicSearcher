package pl.wincenciuk.picsearcher.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import pl.wincenciuk.picsearcher.presentation.screens.DetailsScreen
import pl.wincenciuk.picsearcher.presentation.screens.MainScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController, startDestination = AppScreens.MainScreen.name) {
        composable(AppScreens.MainScreen.name) {
            MainScreen(navController)
        }
        composable(AppScreens.DetailsScreen.name) {
            DetailsScreen()
        }
    }
}
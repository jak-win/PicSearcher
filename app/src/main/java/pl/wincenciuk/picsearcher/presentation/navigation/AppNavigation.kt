package pl.wincenciuk.picsearcher.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.koin.androidx.compose.getViewModel
import pl.wincenciuk.picsearcher.presentation.screens.DetailsScreen
import pl.wincenciuk.picsearcher.presentation.screens.MainScreen
import pl.wincenciuk.picsearcher.presentation.viewmodel.ImageViewModel

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val viewModel = getViewModel<ImageViewModel>()

    NavHost(navController, startDestination = AppScreens.MainScreen.name) {
        composable(AppScreens.MainScreen.name) {
            MainScreen(navController, viewModel)
        }
        composable(AppScreens.DetailsScreen.name) {
            DetailsScreen(viewModel)
        }
    }
}
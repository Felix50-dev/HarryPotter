package com.example.harrypotter.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.harrypotter.ui.mainscreen.DetailsScreen
import com.example.harrypotter.ui.mainscreen.DetailsScreenDestination
import com.example.harrypotter.ui.mainscreen.MainScreen
import com.example.harrypotter.ui.mainscreen.MainScreenDestination

/**
 * Provides Navigation graph for the application.
 */
@Composable
fun HarryPotterNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = MainScreenDestination.route,
        modifier = modifier
    ) {
        composable(route = MainScreenDestination.route) {
            MainScreen(
                onItemClick = {
                    navController.navigate("${DetailsScreenDestination.route}/${it}")
                }
            )
        }
        composable(
            route = DetailsScreenDestination.routeWithArgs,
            arguments = listOf(navArgument(DetailsScreenDestination.characterIdArg) {
                type = NavType.StringType
            })
        ) {
            DetailsScreen(navigateBack = { navController.navigateUp() })
        }
    }
}
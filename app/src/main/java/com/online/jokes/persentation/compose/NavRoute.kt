package com.online.jokes.persentation.compose

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.online.jokes.utils.ScreenRouteConstants

@Composable
fun NavRoute() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = ScreenRouteConstants.SplashScreen,
        modifier = Modifier
            .fillMaxSize()
            .padding(0.dp),
    ) {
        addSplash {
            navController.navigate(route = ScreenRouteConstants.HomeScreen) {
                popUpTo(ScreenRouteConstants.HomeScreen) {
                    inclusive = true
                }
            }
        }
        homeScreen()
    }
}

fun NavGraphBuilder.addSplash(onRedirectScreen: () -> Unit = {}) {
    composable(ScreenRouteConstants.SplashScreen) {
        SplashScreen(onRedirectScreen = onRedirectScreen)
    }
}

fun NavGraphBuilder.homeScreen() {
    composable(ScreenRouteConstants.HomeScreen) {
        HomeScreen()
    }
}

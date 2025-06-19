package com.example.pexelsapp

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.pexelsapp.screens.bottomnavigation.BottomNavigationScreen
import com.example.pexelsapp.screens.details.DetailsScreen
import kotlinx.serialization.Serializable

@Composable
fun PexelsAppHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = BottomNavigation,
        modifier = modifier
    ) {
        composable<BottomNavigation> {
            BottomNavigationScreen(
                modifier = modifier,
                onNavToDetailsScreen = { navController.navigate(Details(it)) }
            )
        }

        composable<Details> { backStackEntry ->
            val details: Details = backStackEntry.toRoute()
            DetailsScreen(
                modifier = modifier,
                photoId = details.photoId,
                onBackPress = { navController.popBackStack() },
                onNavToHomeScreen = { navController.navigate(BottomNavigation) }
            )
        }
    }
}

@Serializable
data object BottomNavigation

@Serializable
data class Details(val photoId: Long)
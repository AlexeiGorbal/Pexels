package com.example.pexelsapp.screens.bottomnavigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.pexelsapp.screens.bookmarks.BookmarksScreen
import com.example.pexelsapp.screens.home.HomeScreen
import kotlinx.serialization.Serializable

@Composable
fun BottomNavigationHost(
    navController: NavHostController,
    onNavToDetailsScreen: (Long) -> Unit,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = Home,
        modifier = modifier
    ) {
        composable<Home> {
            HomeScreen(
                onNavToDetailsScreen = { onNavToDetailsScreen(it) }
            )
        }

        composable<Bookmarks> {
            BookmarksScreen(
                onNavToDetailsScreen = { onNavToDetailsScreen(it) },
                onNavToHomeScreen = { navController.navigate(Home) }
            )
        }
    }
}

@Serializable
object Home

@Serializable
object Bookmarks
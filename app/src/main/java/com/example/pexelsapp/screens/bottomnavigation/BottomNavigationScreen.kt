package com.example.pexelsapp.screens.bottomnavigation

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.pexelsapp.R

@Composable
fun BottomNavigationScreen(
    onNavToDetailsScreen: (Long) -> Unit,
    modifier: Modifier = Modifier,
) {
    val navController = rememberNavController()
    Scaffold(
        modifier = modifier,
        bottomBar = { BottomNavBar(navController) }
    ) { innerPadding ->
        BottomNavigationHost(
            navController = navController,
            onNavToDetailsScreen = onNavToDetailsScreen,
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
private fun BottomNavBar(
    navController: NavController,
    modifier: Modifier = Modifier,
) {
    var selectedDestination by rememberSaveable { mutableIntStateOf(0) }
    NavigationBar(
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.surface
    ) {
        NavItem.entries.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = selectedDestination == index,
                onClick = {
                    selectedDestination = index
                    if (item == NavItem.HOME) {
                        navController.navigate(Home)
                    } else {
                        navController.navigate(Bookmarks)
                    }
                },
                icon = {
                    Icon(
                        painter = painterResource(item.icon),
                        contentDescription = null,
                        modifier = Modifier.size(24.dp)
                    )
                },
                colors = NavigationBarItemDefaults.colors().copy(
                    selectedIndicatorColor = Color.Transparent,
                    selectedIconColor = MaterialTheme.colorScheme.primary,
                    unselectedIconColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                )
            )
        }
    }
}

private enum class NavItem(@DrawableRes val icon: Int) {
    HOME(R.drawable.ic_home),
    BOOKMARKS(R.drawable.ic_bookmark)
}
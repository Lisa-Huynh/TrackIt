package com.example.trackit.ui

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.trackit.NavBarIcon
import com.example.trackit.R
import com.example.trackit.isTrue
import com.example.trackit.navigation.Screens
import com.example.trackit.ui.theme.bottomAppBarBackgroundColor
import com.example.trackit.ui.theme.bottomAppBarContentColor

@Composable
fun BottomAppBar(
    navController: NavController
) {
    BottomNavigation(
        backgroundColor = MaterialTheme.colors.bottomAppBarBackgroundColor,
        contentColor = MaterialTheme.colors.bottomAppBarContentColor,
        elevation = 0.dp
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination

        Screens.values().forEach { screen ->
            BottomNavigationItem(
                selected = currentDestination?.hierarchy?.any {
                    it.route == screen.name
                }.isTrue(),
                onClick = {
                    navController.navigate(screen.name) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                    }
                },
                icon = {
                    val drawableId = when (screen) {
                        Screens.WalletScreen -> R.drawable.ic_card
                        Screens.AnalyticsScreen -> R.drawable.ic_bar_chart
                        else -> null
                    }
                    val image = when (screen) {
                        Screens.HomeScreen -> Icons.Rounded.Home
                        Screens.SettingsScreen -> Icons.Rounded.Settings
                        else -> null
                    }
                    NavBarIcon(
                        drawableId = drawableId,
                        icon = image,
                        description = screen.name
                    )
                }
            )
        }
    }
}

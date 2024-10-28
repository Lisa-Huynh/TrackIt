package com.example.trackit.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.trackit.R
import com.example.trackit.isTrue
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

//        BottomAppBarScreens.entries.forEach { screen ->
//            BottomNavigationItem(
//                selected = currentDestination?.hierarchy?.any {
//                    it.route == screen.name
//                }.isTrue(),
//                onClick = {
//                    navController.navigate(screen.name) {
//                        popUpTo(navController.graph.findStartDestination().id) {
//                            saveState = true
//                        }
//                    }
//                },
//                icon = {
//                    val drawableId = when (screen) {
//                        BottomAppBarScreens.WALLET -> R.drawable.ic_card
//                        BottomAppBarScreens.ANALYTICS -> R.drawable.ic_bar_chart
//                        else -> null
//                    }
//                    val image = when (screen) {
//                        BottomAppBarScreens.HOME -> Icons.Rounded.Home
//                        BottomAppBarScreens.SETTINGS -> Icons.Rounded.Settings
//                        else -> null
//                    }
//                    NavBarIcon(
//                        drawableId = drawableId,
//                        icon = image,
//                        description = screen.name
//                    )
//                }
//            )
//        }
    }
}

@Composable
fun NavBarIcon(
    @DrawableRes drawableId: Int? = null,
    icon: ImageVector? = null,
    description: String
) {
    assert(drawableId != null || icon != null)
    if (drawableId != null) {
        Icon(
            painterResource(id = drawableId),
            contentDescription = description,
            modifier = Modifier
                .size(50.dp),
        )
    } else if (icon != null) {
        Icon(
            icon,
            contentDescription = description,
            modifier = Modifier
                .size(50.dp),
        )
    }
}

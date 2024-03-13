package com.example.trackit.navigation

import androidx.navigation.NavController

class ScreenNav(navController: NavController) {
    val userInfoScreen: () -> Unit = {
        navController.navigate(route = "user info screen") {
            popUpTo("user info screen")
        }
    }

    val homeScreen: () -> Unit = {
        navController.navigate(route = "home screen") {
            popUpTo("home screen")
        }
    }

    val walletScreen: () -> Unit = {
        navController.navigate(route = "wallet screen") {
            popUpTo("wallet screen")
        }
    }

//    val navigateTo: (Screens) -> Unit = {
//        when (it) {
//            Screens.HomeScreen -> navController.navigate(route = Screens.HomeScreen.toString())
//        }
//
//    }
}

enum class BottomAppBarScreens {
    HOME,
    WALLET,
    ANALYTICS,
    SETTINGS,
}
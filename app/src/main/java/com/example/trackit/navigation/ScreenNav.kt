package com.example.trackit.navigation

import androidx.navigation.NavController

class ScreenNav(navController: NavController) {
    val userInfoScreen: () -> Unit = {
        navController.navigate(route = "user info screen") {
            popUpTo("user info screen")
        }
    }

    val homeScreen: () -> Unit = {
        navController.navigate(route = BottomAppBarScreens.HOME.toString()) {
            popUpTo(BottomAppBarScreens.HOME.toString())
        }
    }

    val walletScreen: () -> Unit = {
        navController.navigate(route = BottomAppBarScreens.WALLET.toString()) {
            popUpTo(BottomAppBarScreens.WALLET.toString())
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
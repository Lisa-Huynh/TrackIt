package com.example.trackit.navigation

import androidx.navigation.NavController

class ScreenNav(navController: NavController) {
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

enum class Screens {
    HomeScreen,
    WalletScreen
}
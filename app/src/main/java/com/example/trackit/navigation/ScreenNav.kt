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

}

enum class Screens {
    HomeScreen,
    WalletScreen,
    AnalyticsScreen,
    SettingsScreen
}
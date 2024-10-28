package com.example.trackit.navigation

sealed class NavController {
    data object GoBack : NavController()
    data class GoTo(val route: Route) : NavController()
}
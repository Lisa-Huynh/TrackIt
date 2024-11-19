package com.example.trackit.navigation

import kotlinx.serialization.Serializable

sealed class Route {
    @Serializable
    data object Home : Route()

    @Serializable
    data object Login : Route()

    @Serializable
    data object UserInfo : Route()

    @Serializable
    data object Profile : Route()
}
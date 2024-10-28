package com.example.trackit.navigation

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

object Navigator {
    private val _navigationStream = MutableSharedFlow<NavController>()
    val navigationStream = _navigationStream.asSharedFlow()

    suspend fun goBack() {
        _navigationStream.emit(NavController.GoBack)
    }

    suspend fun goTo(route: Route) {
        _navigationStream.emit(NavController.GoTo(route))
    }
}
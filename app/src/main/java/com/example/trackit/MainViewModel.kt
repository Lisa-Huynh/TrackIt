package com.example.trackit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trackit.navigation.NavController
import com.example.trackit.navigation.Navigator
import com.example.trackit.navigation.Route
import com.example.trackit.repositories.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class MainViewModel @Inject constructor(
    private val authRepository: AuthRepository,
) : ViewModel() {

    private val _navigationStream = MutableStateFlow<NavController?>(null)
    val navigationStream = _navigationStream.asStateFlow()

    init {
        viewModelScope.launch {
            Navigator.navigationStream.collectLatest {
                _navigationStream.update { it }
            }
        }
        viewModelScope.launch {
            authRepository.getUser()?.let { Navigator.goTo(route = Route.Home) }
        }
    }

    fun onNavigationHandled() {
        _navigationStream.update { null }
    }
}
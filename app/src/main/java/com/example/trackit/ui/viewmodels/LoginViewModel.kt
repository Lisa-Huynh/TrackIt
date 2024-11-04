package com.example.trackit.ui.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trackit.navigation.Navigator
import com.example.trackit.navigation.Route
import com.example.trackit.repositories.AccountRepository
import com.example.trackit.repositories.AuthRepository
import com.example.trackit.util.LoginUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val accountRepository : AccountRepository,
): ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState("bbb", ""))
    val uiState = _uiState.asStateFlow()

    fun onEmailChange(newEmail: String) {
        viewModelScope.launch {
            _uiState.emit(LoginUiState(newEmail, uiState.value.password))
        }
    }

    fun onPasswordChange(newPassword: String) {
        viewModelScope.launch {
            _uiState.emit(LoginUiState(uiState.value.email, newPassword))
        }
    }

    fun onLoginClick() {
        viewModelScope.launch {
            val user = authRepository.emailLogin(uiState.value.email, uiState.value.password)
            if (user != null) {
                if (accountRepository.getProfile(user.uid) == null) {
                    Navigator.goTo(Route.UserInfo)
                } else {
                    Navigator.goTo(Route.Home)
                }
            }
        }
    }

    fun onSignUpClick() {
        viewModelScope.launch {
            val user = authRepository.emailSignUp(uiState.value.email, uiState.value.password)
            if (user != null) {
                Navigator.goTo(Route.UserInfo)
            }
        }
    }
}
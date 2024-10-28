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

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val accountRepository : AccountRepository,
): ViewModel() {

    var uiState = mutableStateOf(LoginUiState("", ""))
        private set

    fun onEmailChange(newEmail: String) {
        uiState.value = uiState.value.copy(email = newEmail)
    }

    fun onPasswordChange(newPassword: String) {
        uiState.value = uiState.value.copy(password = newPassword)
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
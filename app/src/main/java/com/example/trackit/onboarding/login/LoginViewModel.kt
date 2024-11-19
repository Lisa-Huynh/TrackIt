package com.example.trackit.onboarding.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trackit.data.models.Profile
import com.example.trackit.navigation.Navigator
import com.example.trackit.navigation.Route
import com.example.trackit.data.repositories.ProfileRepository
import com.example.trackit.data.repositories.AuthRepository
import com.example.trackit.util.LoginUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val profileRepository : ProfileRepository,
): ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState("", ""))
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
                if (profileRepository.getProfile(user.uid) is Profile.Blank) {
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
package com.example.trackit.ui.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trackit.navigation.ScreenNav
import com.example.trackit.repositories.AuthRepository
import com.example.trackit.util.LoginUiState
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.mapLatest

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository,
): ViewModel() {
    private val _currentUser = MutableSharedFlow<FirebaseUser?>()
    val currentUser: SharedFlow<FirebaseUser?>
        get() = _currentUser

    init {
        getUser()
        currentUser.mapLatest { user ->
            if (user != null) {
                screenNav.userInfoScreen.invoke()
            }
        }
    }

    private fun getUser(){
        viewModelScope.launch {
            authRepository.getUser().collectLatest { user -> _currentUser.emit(user) }
        }
    }

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
            _currentUser.emit(authRepository.emailLogin(uiState.value.email, uiState.value.password))
        }
    }

    fun onSignUpClick() {
        viewModelScope.launch {
            _currentUser.emit(authRepository.emailSignUp(uiState.value.email, uiState.value.password))
        }
    }
}
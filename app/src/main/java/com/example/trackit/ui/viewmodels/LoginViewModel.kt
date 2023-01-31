package com.example.trackit.ui.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trackit.repositories.AuthRepository
import com.example.trackit.util.LoginUiState
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository
): ViewModel() {
    private val _currentUser = MutableLiveData<FirebaseUser?>()
    val currentUser: LiveData<FirebaseUser?>
        get() = _currentUser

    init {
        getUser()
    }

    private fun getUser(){
        viewModelScope.launch {
            authRepository.getUser().collectLatest { user -> _currentUser.value = user }
        }
    }

    var uiState = mutableStateOf(LoginUiState())
        private set

    fun onEmailChange(newEmail: String) {
        uiState.value = uiState.value.copy(email = newEmail)
    }

    fun onPasswordChange(newPassword: String) {
        uiState.value = uiState.value.copy(password = newPassword)
    }

    fun onLoginClick(email: String, password: String) {
        viewModelScope.launch {
            authRepository.emailLogin(email, password)
        }
    }

    fun onSignUpClick(email: String, password: String) {
        viewModelScope.launch {
            authRepository.emailSignUp(email, password)
        }
    }
}
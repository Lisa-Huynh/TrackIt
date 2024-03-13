package com.example.trackit.ui.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trackit.data.models.Profile
import com.example.trackit.repositories.AccountRepository
import com.example.trackit.repositories.AuthRepository
import com.example.trackit.util.ProfileUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val accountRepository : AccountRepository,
    private val authRepository: AuthRepository
): ViewModel() {
    var uiState = mutableStateOf(ProfileUiState("", ""))
        private set

    fun onFirstNameChange(firstName: String) {
        uiState.value = uiState.value.copy(firstName = firstName)
    }

    fun onLastNameChange(lastName: String) {
        uiState.value = uiState.value.copy(lastName = lastName)
    }

    fun onSubmitInfoClick() {
        viewModelScope.launch {
            submitInfo()
        }
    }

    private suspend fun submitInfo() {
        authRepository.getUser().collectLatest { user ->
            user?.uid?.let { userId ->
                accountRepository.addAccount(
                    Profile(
                        id = userId,
                        firstName = uiState.value.firstName,
                        lastName = uiState.value.lastName
                    )
                )
            }
        }
    }
}
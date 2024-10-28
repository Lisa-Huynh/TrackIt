package com.example.trackit.ui.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trackit.data.models.Profile
import com.example.trackit.navigation.Navigator
import com.example.trackit.navigation.Route
import com.example.trackit.repositories.AccountRepository
import com.example.trackit.util.ProfileUiState
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class UserInfoViewModel @Inject constructor(
    private val accountRepository : AccountRepository
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
            FirebaseAuth.getInstance().currentUser?.let { user ->
                user.uid.let { userId ->
                    accountRepository.addAccount(
                        Profile(
                            id = userId,
                            firstName = uiState.value.firstName,
                            lastName = uiState.value.lastName
                        )
                    )
                    Navigator.goTo(Route.Home)
                }
            }
        }
    }
}
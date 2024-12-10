package com.example.trackit.onboarding.userinfo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trackit.data.models.Profile
import com.example.trackit.navigation.Navigator
import com.example.trackit.navigation.Route
import com.example.trackit.data.repositories.ProfileRepository
import com.example.trackit.util.ProfileUiState
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class UserInfoViewModel @Inject constructor(
    private val profileRepository : ProfileRepository
): ViewModel() {
    private val _uiState = MutableStateFlow(ProfileUiState("", ""))
    val uiState = _uiState.asStateFlow()

    fun onFirstNameChange(firstName: String) {
        viewModelScope.launch {
            _uiState.emit(ProfileUiState(firstName, uiState.value.lastName))
        }
    }

    fun onLastNameChange(lastName: String) {
        viewModelScope.launch {
            _uiState.emit(ProfileUiState(uiState.value.firstName, lastName))
        }
    }

    fun onSubmitInfoClick() {
        viewModelScope.launch {
            FirebaseAuth.getInstance().currentUser?.let { user ->
                user.uid.let { userId ->
                    profileRepository.addAccount(
                        Profile.Loaded(
                            id = userId,
                            firstName = uiState.value.firstName,
                            lastName = uiState.value.lastName,
                            cards = emptyList(),
                        )
                    )
                }
            }
            Navigator.goTo(Route.Home)
        }
    }
}
package com.example.trackit.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trackit.data.models.Profile
import com.example.trackit.repositories.AccountRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

import kotlinx.coroutines.launch

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val accountRepository : AccountRepository,
): ViewModel() {
    private val _currentProfile = MutableStateFlow(Profile("", "", ""))
    val currentProfile : StateFlow<Profile> = _currentProfile

    init {
        viewModelScope.launch {
            _currentProfile.emit(accountRepository.getProfile())
        }
    }
}
package com.example.trackit.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trackit.data.models.Profile
import com.example.trackit.repositories.AccountRepository
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val accountRepository : AccountRepository,
): ViewModel() {

    private val _profileStream = MutableStateFlow<Profile>(Profile.Blank)
    val profileStream = _profileStream.asStateFlow()

    init {
        viewModelScope.launch {
            val accountId = FirebaseAuth.getInstance().currentUser?.uid!!
            _profileStream.emit(accountRepository.getProfile(accountId))
        }
    }
}
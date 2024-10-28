package com.example.trackit.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trackit.data.models.Profile
import com.example.trackit.repositories.AccountRepository
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val accountRepository : AccountRepository,
): ViewModel() {

    lateinit var profile: Profile

    init {
        viewModelScope.launch {
            val accountId = FirebaseAuth.getInstance().currentUser?.uid!!
            profile = accountRepository.getProfile(accountId)
        }
    }
}
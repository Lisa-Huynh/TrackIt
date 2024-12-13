package com.example.trackit.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trackit.data.models.Card
import com.example.trackit.data.models.Profile
import com.example.trackit.navigation.Navigator
import com.example.trackit.navigation.Route
import com.example.trackit.data.repositories.ProfileRepository
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import java.text.DateFormat.getDateInstance
import java.util.Date
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val profileRepository : ProfileRepository,
): ViewModel() {

    private val _profileStream = MutableStateFlow<Profile>(Profile.Blank)
    val profileStream = _profileStream.asStateFlow()

    private val formatter = getDateInstance()
    private val date = Date()
    val currentDate: String = formatter.format(date)

    init {
        viewModelScope.launch {
            val accountId = FirebaseAuth.getInstance().currentUser?.uid!!
            _profileStream.emit(profileRepository.getProfile(accountId))
        }
    }

    fun onProfileIconClick() {
        viewModelScope.launch {
            Navigator.goTo(Route.Profile)
        }
    }

    fun onWalletClick() {
        viewModelScope.launch {
            Navigator.goTo(Route.Wallet)
        }
    }
}
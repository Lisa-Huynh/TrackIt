package com.example.trackit.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trackit.navigation.Navigator
import com.example.trackit.navigation.Route
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class ProfileViewModel @Inject constructor() : ViewModel() {

    fun onLogoutButtonClick() {
        FirebaseAuth.getInstance().signOut()
        viewModelScope.launch {
            Navigator.goTo(Route.Login)
        }
    }
}
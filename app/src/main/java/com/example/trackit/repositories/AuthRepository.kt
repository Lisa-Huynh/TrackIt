package com.example.trackit.repositories

import com.example.trackit.services.AccountService
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val accountService: AccountService
) {
    private val _userFlow = MutableSharedFlow<FirebaseUser?>()
    fun getUser(): SharedFlow<FirebaseUser?> = _userFlow.asSharedFlow()

    init {
        Firebase.auth.signOut()
    }

    suspend fun emailLogin(email: String, password: String) {
        if (accountService.authenticate(email, password)) {
            _userFlow.emit(Firebase.auth.currentUser)
        }
    }

    suspend fun emailSignUp(email: String, password: String) {
        if (accountService.signUp(email, password)) {
            _userFlow.emit(Firebase.auth.currentUser)
        }
    }
}
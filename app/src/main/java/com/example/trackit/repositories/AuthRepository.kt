package com.example.trackit.repositories

import com.example.trackit.services.AccountService
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepository @Inject constructor(
    private val accountService: AccountService
) {
    private val _userFlow = MutableSharedFlow<FirebaseUser?>()
    suspend fun getUser(): SharedFlow<FirebaseUser?> {
        _userFlow.emit(Firebase.auth.currentUser)
        return _userFlow.asSharedFlow()
    }

    init {
        Firebase.auth.signOut()
    }

    suspend fun emailLogin(email: String, password: String): FirebaseUser? {
        if (accountService.authenticate(email, password)) {
            return Firebase.auth.currentUser
        }
        return null
    }

    suspend fun emailSignUp(email: String, password: String) {
        if (accountService.signUp(email, password)) {
            _userFlow.emit(Firebase.auth.currentUser)
        }
    }
}
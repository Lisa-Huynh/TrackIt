package com.example.trackit.repositories

import com.example.trackit.services.AccountService
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepository @Inject constructor(
    private val accountService: AccountService
) {
    private val auth = FirebaseAuth.getInstance()
    private val _userFlow = MutableSharedFlow<FirebaseUser?>()

    val userFlow: SharedFlow<FirebaseUser?>
        get() = _userFlow

    suspend fun getUser(): SharedFlow<FirebaseUser?> {
        _userFlow.emit(auth.currentUser)
        return _userFlow.asSharedFlow()
    }

//    init {
//        Firebase.auth.signOut()
//    }

    suspend fun emailLogin(email: String, password: String): FirebaseUser? {
        if (accountService.authenticate(email, password)) {
            _userFlow.emit(auth.currentUser)
            return auth.currentUser
        }
        return null
    }

    suspend fun emailSignUp(email: String, password: String): FirebaseUser? {
        if (accountService.signUp(email, password)) {
            _userFlow.emit(auth.currentUser)
            return auth.currentUser
        }
        return null
    }
}
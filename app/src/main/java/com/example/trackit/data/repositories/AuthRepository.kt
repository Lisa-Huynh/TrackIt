package com.example.trackit.data.repositories

import com.example.trackit.services.AccountService
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepository @Inject constructor(
    private val accountService: AccountService
) {
    private val auth = FirebaseAuth.getInstance()

    fun getUser(): FirebaseUser? {
        return auth.currentUser
    }

    suspend fun emailLogin(email: String, password: String): FirebaseUser? {
        return accountService.authenticate(email, password)?.user
    }

    suspend fun emailSignUp(email: String, password: String): FirebaseUser? {
        return accountService.signUp(email, password)?.user
    }
}
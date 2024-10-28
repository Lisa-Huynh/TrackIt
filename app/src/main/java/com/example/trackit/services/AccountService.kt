package com.example.trackit.services

import com.google.firebase.auth.AuthResult

interface AccountService {
    suspend fun authenticate(email: String, password: String): AuthResult?

    suspend fun signUp(email: String, password: String): AuthResult?
}
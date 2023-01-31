package com.example.trackit.services

interface AccountService {
    suspend fun authenticate(email: String, password: String): Boolean

    suspend fun signUp(email: String, password: String): Boolean
}
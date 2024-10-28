package com.example.trackit.services

import android.content.Context
import android.widget.Toast
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AccountServiceImpl @Inject constructor(
    @ApplicationContext val applicationContext: Context
): AccountService {
    override suspend fun authenticate(email: String, password: String): AuthResult? {
        return try {
            Firebase.auth.signInWithEmailAndPassword(email, password).await()
        } catch (_: Exception) {
            Toast.makeText(applicationContext, "Login failed.",
                Toast.LENGTH_SHORT).show()
            null
        }
    }

    override suspend fun signUp(email: String, password: String): AuthResult? {
        return try {
            Firebase.auth.createUserWithEmailAndPassword(email, password).await()
        } catch (_: Exception) {
            Toast.makeText(applicationContext, "Sign Up failed.",
                Toast.LENGTH_SHORT).show()
            null
        }
    }
}
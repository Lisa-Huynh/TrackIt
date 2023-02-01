package com.example.trackit.services

import android.content.Context
import android.widget.Toast
import com.example.trackit.data.models.Profile
import com.example.trackit.data.models.Profile.Companion.fromMap
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class StorageServiceImpl @Inject constructor(
    @ApplicationContext val applicationContext: Context
): StorageService {
    override suspend fun addAccount(profile: Profile) {
        try {
            Firebase.firestore.collection("Accounts").document(profile.id).set({
                "firstName" to profile.firstName
                "lastName" to profile.lastName
            }).await()
        } catch (_: Exception) {
            Toast.makeText(applicationContext, "failed to add account.",
                Toast.LENGTH_SHORT).show()
        }
    }

    override suspend fun getAccount(accountId: String): Profile? {
        return try {
            val reference = Firebase.firestore.collection("Accounts").document(accountId).get().await()
            reference.data?.let { fromMap(it) }
        } catch (_: Exception) {
            Toast.makeText(applicationContext, "failed to get account.",
                Toast.LENGTH_SHORT).show()
            null
        }
    }
}
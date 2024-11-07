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
    override suspend fun addAccount(profile: Profile.Loaded) {
        try {
            Firebase.firestore.collection("Accounts").document(profile.id).set({
                "firstName" to profile.firstName
                "lastName" to profile.lastName
            }).await()
//            val reference = Firebase.firestore.collection("Accounts").document(profile.id).get().await()
//            val temp = reference.get("firstName").toString()
//            temp
        } catch (e: Exception) {
            Toast.makeText(applicationContext, e.message, Toast.LENGTH_SHORT).show()
        }
    }

    override suspend fun getAccount(accountId: String): Profile {
        return try {
            val reference = Firebase.firestore.collection("Accounts").document(accountId).get().await()
            if (reference.get("firstName").toString() == "null") {
                Profile.Blank
            } else {
                fromMap(reference.data!!)
            }
        } catch (e: Exception) {
            Toast.makeText(applicationContext, e.message, Toast.LENGTH_SHORT).show()
            Profile.Blank
        }
    }
}
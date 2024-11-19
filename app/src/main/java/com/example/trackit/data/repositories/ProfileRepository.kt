package com.example.trackit.data.repositories

import com.example.trackit.data.models.Profile
import com.example.trackit.services.StorageService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProfileRepository @Inject constructor(
    private val storageService: StorageService
) {

    suspend fun getProfile(accountId: String): Profile {
        return storageService.getAccount(accountId)
    }

    suspend fun addAccount(profile: Profile.Loaded) {
        storageService.addAccount(profile)
    }
}
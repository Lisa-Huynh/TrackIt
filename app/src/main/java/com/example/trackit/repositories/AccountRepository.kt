package com.example.trackit.repositories

import com.example.trackit.data.models.Profile
import com.example.trackit.services.StorageService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AccountRepository @Inject constructor(
    private val storageService: StorageService
) {

    suspend fun getProfile(accountId: String): Profile? {
        return storageService.getAccount(accountId)
    }

    suspend fun addAccount(profile: Profile) {
        storageService.addAccount(profile)
    }
}
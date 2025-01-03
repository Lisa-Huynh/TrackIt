package com.example.trackit.services

import com.example.trackit.data.models.Profile

interface StorageService {
    suspend fun addAccount(profile: Profile.Loaded)

    suspend fun getAccount(accountId: String): Profile
}
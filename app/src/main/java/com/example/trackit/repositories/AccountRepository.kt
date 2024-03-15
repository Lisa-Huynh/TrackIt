package com.example.trackit.repositories

import com.example.trackit.data.models.Profile
import com.example.trackit.services.StorageService
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.first

@Singleton
class AccountRepository @Inject constructor(
    private val storageService: StorageService
) {
    private val _profileFlow = MutableSharedFlow<Profile>(replay = 1)
    val profileFlow: SharedFlow<Profile> = _profileFlow.asSharedFlow()

    suspend fun getProfile(): Profile {
        return profileFlow.first()
    }

    suspend fun addAccount(profile: Profile) {
        storageService.addAccount(profile)
        _profileFlow.emit(profile)
    }
}
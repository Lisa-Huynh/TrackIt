package com.example.trackit.di

import com.example.trackit.services.AccountService
import com.example.trackit.services.AccountServiceImpl
import com.example.trackit.services.StorageService
import com.example.trackit.services.StorageServiceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class AccountModule {

    @Binds
    abstract fun bindAccountService(
        accountServiceImpl: AccountServiceImpl
    ): AccountService

    @Binds
    abstract fun bindStorageService(
        storageServiceImpl: StorageServiceImpl
    ): StorageService
}
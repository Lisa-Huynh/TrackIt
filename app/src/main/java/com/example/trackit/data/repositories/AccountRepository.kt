package com.example.trackit.data.repositories

import com.example.trackit.data.database.AccountDao
import com.example.trackit.data.models.Account
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import kotlinx.coroutines.flow.first

@ViewModelScoped
class AccountRepository @Inject constructor(
    private val accountDao: AccountDao
) {
    suspend fun getAllAccounts(): List<Account> {
        return accountDao.getAllAccounts().first()
    }

    suspend fun addAccount(account: Account) {
        accountDao.addAccount(account)
    }

    suspend fun deleteAccount(account: Account) {
        accountDao.deleteAccount(account)
    }
}
package com.example.trackit.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.trackit.data.models.Account
import kotlinx.coroutines.flow.Flow

@Dao
interface AccountDao {
    @Query("SELECT * FROM item_table ORDER BY id ASC")
    fun getAllAccounts(): Flow<List<Account>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addAccount(account: Account)

    @Delete
    suspend fun deleteAccount(account: Account)
}
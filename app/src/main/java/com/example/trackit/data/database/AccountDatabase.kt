package com.example.trackit.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.trackit.data.models.Account

@Database(entities = [Account::class], version = 1, exportSchema = false)
abstract class AccountDatabase: RoomDatabase() {
    abstract fun accountDao(): AccountDao
}
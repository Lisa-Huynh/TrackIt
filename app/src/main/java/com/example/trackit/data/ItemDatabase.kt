package com.example.trackit.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.trackit.data.models.Item

@Database(entities = [Item::class], version = 1, exportSchema = false)
abstract class ItemDatabase: RoomDatabase() {
    abstract fun itemDao(): ItemDao
}
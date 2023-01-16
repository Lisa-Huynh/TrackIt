package com.example.trackit.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "item_table")
data class Item (
    @PrimaryKey(autoGenerate = true)
    // id should be a String
    val id: Int = 0,
    val accessToken: String = ""
)
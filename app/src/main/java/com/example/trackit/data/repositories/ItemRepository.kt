package com.example.trackit.data.repositories

import com.example.trackit.data.database.ItemDao
import com.example.trackit.data.models.Item
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ViewModelScoped
class ItemRepository @Inject constructor(
    private val itemDao: ItemDao
) {
    val getAllItems: Flow<List<Item>> = itemDao.getAllItems()

    suspend fun addAccount(item: Item) {
        itemDao.addAccount(item)
    }

    suspend fun deleteAccount(item: Item) {
        itemDao.deleteAccount(item)
    }
}
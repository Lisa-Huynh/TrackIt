package com.example.trackit.di

import android.content.Context
import androidx.room.Room
import com.example.trackit.data.database.ItemDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        ItemDatabase::class.java,
        "item_database"
    ).build()

    @Singleton
    @Provides
    fun provideDao(database: ItemDatabase) = database.itemDao()
}
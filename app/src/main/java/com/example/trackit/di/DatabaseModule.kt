package com.example.trackit.di

import android.content.Context
import androidx.room.Room
import com.example.trackit.data.database.AccountDatabase
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
        AccountDatabase::class.java,
        "item_database"
    ).build()

    @Singleton
    @Provides
    fun provideDao(database: AccountDatabase) = database.itemDao()
}
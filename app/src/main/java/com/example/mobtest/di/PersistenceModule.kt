package com.example.mobtest.di

import android.app.Application
import androidx.room.Room
import com.example.mobtest.data.MobtestDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PersistenceModule {

    @Provides
    @Singleton
    fun provideDatabase(application: Application) =
        Room.databaseBuilder(application, MobtestDatabase::class.java, "mobtest").build()

    @Provides
    @Singleton
    fun provideUserDao(mobtestDatabase: MobtestDatabase) = mobtestDatabase.userDao()
}
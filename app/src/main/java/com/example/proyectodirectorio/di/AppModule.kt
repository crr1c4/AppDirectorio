package com.example.proyectodirectorio.di

import android.content.Context
import androidx.room.Room.databaseBuilder
import com.example.proyectodirectorio.room.ContactoDatabase
import com.example.proyectodirectorio.room.ContactoDatabaseDao
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext



@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Singleton
    @Provides
    fun provideCronosDao(contactoDatabase: ContactoDatabase): ContactoDatabaseDao {
        return contactoDatabase.contactoDao()
    }

    @Singleton
    @Provides
    fun provideCronosDatabase(@ApplicationContext context: Context): ContactoDatabase {
        return databaseBuilder(
            context,
            ContactoDatabase::class.java,
            "contactos"
        ).fallbackToDestructiveMigration()
            .build()
    }

}
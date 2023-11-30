package com.example.todolistapp.di

import android.content.Context
import com.example.todolistapp.data.AppDatabase
import com.example.todolistapp.repository.TodoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return AppDatabase.getInstance(appContext)
    }

    @Singleton
    @Provides
    fun provideRepository(database: AppDatabase): TodoRepository {
        return TodoRepository(database.todoDao())
    }
}

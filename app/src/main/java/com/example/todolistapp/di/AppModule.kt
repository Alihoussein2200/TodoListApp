package com.example.todolistapp.di

import com.example.todolistapp.data.AppDatabase
import com.example.todolistapp.repository.TodoRepository
import com.example.todolistapp.viewmodel.TodoViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { AppDatabase.getDatabase(androidContext()).todoDao() }
    single { TodoRepository(get()) }
    viewModel { TodoViewModel(get()) }
}

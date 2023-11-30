package com.example.todolistapp.viewmodel

import androidx.lifecycle.*
import com.example.todolistapp.data.TodoEntity
import com.example.todolistapp.repository.TodoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoViewModel @Inject constructor(private val repository: TodoRepository) : ViewModel() {
    val allTodos: LiveData<List<TodoEntity>> = repository.getAllTodos

    fun update(todo: TodoEntity) {
        viewModelScope.launch {
            repository.update(todo)
        }
    }

    fun insert(todo: TodoEntity) {
        viewModelScope.launch {
            repository.insert(todo)
        }
    }
}

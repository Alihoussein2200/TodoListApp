package com.example.todolistapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolistapp.data.TodoEntity
import com.example.todolistapp.repository.TodoRepository
import kotlinx.coroutines.launch
import androidx.lifecycle.*

class TodoViewModel(private val repository: TodoRepository) : ViewModel() {
    val allTodos = repository.allTodos

    private val _searchQuery = MutableLiveData("")

    val filteredTodos: LiveData<List<TodoEntity>> = MediatorLiveData<List<TodoEntity>>().apply {
        addSource(allTodos) { todos ->
            value = filterTodos(todos, _searchQuery.value ?: "")
        }
        addSource(_searchQuery) { query ->
            value = filterTodos(allTodos.value ?: emptyList(), query)
        }
    }

    private fun filterTodos(todos: List<TodoEntity>, query: String): List<TodoEntity> {
        return if (query.isEmpty()) {
            todos
        } else {
            todos.filter { it.title.contains(query, ignoreCase = true) }
        }
    }

    fun setSearchQuery(query: String) {
        _searchQuery.value = query
    }

    fun insert(todo: TodoEntity) = viewModelScope.launch {
        repository.insert(todo)
    }

    fun update(todo: TodoEntity) = viewModelScope.launch {
        repository.update(todo)
    }

    fun delete(todo: TodoEntity) = viewModelScope.launch {
        repository.delete(todo)
    }
}

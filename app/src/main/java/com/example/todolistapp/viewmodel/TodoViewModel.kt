package com.example.todolistapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.todolistapp.data.TodoEntity
import com.example.todolistapp.repository.TodoRepository
import kotlinx.coroutines.launch


class TodoViewModel(private val repository: TodoRepository) : ViewModel() {
    val allTodos = repository.allTodos

    // For searching
    private val _searchQuery = MutableLiveData("")

    // For filtering
    val filteredTodos: LiveData<List<TodoEntity>> = Transformations.switchMap(_searchQuery) { query ->
        if (query.isEmpty()) {
            _allTodos
        } else {
            Transformations.map(_allTodos) { todos ->
                todos.filter { it.title.contains(query, ignoreCase = true) }
            }
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

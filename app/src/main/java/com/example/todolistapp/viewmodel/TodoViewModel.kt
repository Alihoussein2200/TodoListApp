package com.example.todolistapp.viewmodel

import androidx.lifecycle.*
import com.example.todolistapp.data.TodoEntity
import com.example.todolistapp.repository.TodoRepository
import kotlinx.coroutines.launch

class TodoViewModel(private val repository: TodoRepository) : ViewModel() {

    private val allTodos: LiveData<List<TodoEntity>> = repository.allTodos
    val errorMessage = MutableLiveData<String>()

    private val _searchQuery = MutableLiveData("")
    private val _filterStatus = MutableLiveData<Boolean?>(null)
    private val _filterTag = MutableLiveData<String?>(null)

    val filteredTodos: LiveData<List<TodoEntity>> = _searchQuery.switchMap { query ->
        if (query.isEmpty()) {
            // If search query is empty, apply other filters
            when (_filterStatus.value) {
                null -> repository.allTodos
                else -> repository.getTodosByStatus(_filterStatus.value!!)
            }
        } else {
            // If search query is not empty, perform search and apply filters
            repository.searchTodos(query).map { todos ->
                todos.filter { todo ->
                    _filterStatus.value?.let { status -> todo.completed == status } ?: true
                }
            }
        }
    }


    private fun filterTodos(todos: List<TodoEntity>, query: String, status: Boolean?, tag: String?): List<TodoEntity> {
        return todos.filter {
            it.title.contains(query, ignoreCase = true) &&
                    (status == null || it.completed == status) &&
                    (tag == null || it.tags?.contains(tag) == true)
        }
    }

    fun setSearchQuery(query: String) {
        _searchQuery.value = query
    }

    fun setFilterStatus(status: Boolean?) {
        _filterStatus.value = status
    }

    fun setFilterTag(tag: String?) {
        _filterTag.value = tag
    }

    fun insertTodo(todo: TodoEntity) {
        viewModelScope.launch {
            repository.insertTodo(todo).handleResult()
        }
    }

    fun updateTodo(todo: TodoEntity) {
        viewModelScope.launch {
            repository.updateTodo(todo).handleResult()
        }
    }

    fun deleteTodo(todo: TodoEntity) {
        viewModelScope.launch {
            repository.deleteTodo(todo).handleResult()
        }
    }

    private fun Result<Unit>.handleResult() {
        onSuccess {
            // Handle success
        }.onFailure { e ->
            // Handle error, update error message LiveData
            errorMessage.value = "Operation failed: ${e.message}"
        }
    }

    // TODO: Implement methods for Giphy API integration
}

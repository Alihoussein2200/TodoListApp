package com.example.todolistapp.repository

import androidx.lifecycle.LiveData
import com.example.todolistapp.data.TodoDao
import com.example.todolistapp.data.TodoEntity

class TodoRepository(private val todoDao: TodoDao) {
    val allTodos = todoDao.getAllTodos()


    suspend fun insertTodo(todo: TodoEntity): Result<Unit> {
        return try {
            todoDao.insert(todo)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun updateTodo(todo: TodoEntity): Result<Unit> {
        return try {
            todoDao.update(todo)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun deleteTodo(todo: TodoEntity): Result<Unit> {
        return try {
            todoDao.delete(todo)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    fun searchTodos(searchQuery: String): LiveData<List<TodoEntity>> {
        return todoDao.searchTodos(searchQuery)
    }
    fun getTodosByStatus(status: Boolean) : LiveData<List<TodoEntity>> {
        return todoDao.getTodosByStatus(status)
    }
    fun getTodosByTag(tag: String): LiveData<List<TodoEntity>> {
        return todoDao.getTodosByTag(tag)
    }
    // Giphy API integration methods (to be implemented)
    // TODO: Implement methods to fetch and assign GIFs from Giphy API
}

package com.example.todolistapp.repository

import com.example.todolistapp.data.TodoDao
import com.example.todolistapp.data.TodoEntity

class TodoRepository(private val todoDao: TodoDao) {
    val allTodos = todoDao.getAllTodos()
    // Repository implementation

    suspend fun insert(todo: TodoEntity) {
        todoDao.insert(todo)
    }

    suspend fun update(todo: TodoEntity) {
        todoDao.update(todo)
    }


    suspend fun delete(todo: TodoEntity) {
        todoDao.delete(todo)
    }


}

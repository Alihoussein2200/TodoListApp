package com.example.todolistapp.repository

import androidx.lifecycle.LiveData
import com.example.todolistapp.data.TodoDao
import com.example.todolistapp.data.TodoEntity

class TodoRepository(private val todoDao: TodoDao) {

    val getAllTodos: LiveData<List<TodoEntity>> = todoDao.getAllTodos()

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

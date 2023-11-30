package com.example.todolistapp.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TodoDao {
    @Query("SELECT * FROM todos")
    fun getAllTodos(): LiveData<List<TodoEntity>>

    @Insert
    suspend fun insert(todo: TodoEntity)

    @Update
    suspend fun update(todo: TodoEntity)
    @Delete
    suspend fun delete(todo: TodoEntity)

}
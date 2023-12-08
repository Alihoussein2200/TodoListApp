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

    // Method to filter todos by completion status
    @Query("SELECT * FROM todos WHERE completed = :status")
    fun getTodosByStatus(status: Boolean): LiveData<List<TodoEntity>>

    // Method to search todos by title or description
    @Query("SELECT * FROM todos WHERE title LIKE '%' || :searchQuery || '%' OR description LIKE '%' || :searchQuery || '%'")
    fun searchTodos(searchQuery: String): LiveData<List<TodoEntity>>

    // Method to filter todos by tags
    @Query("SELECT * FROM todos WHERE tags LIKE '%' || :tag || '%'")
    fun getTodosByTag(tag: String): LiveData<List<TodoEntity>>

}
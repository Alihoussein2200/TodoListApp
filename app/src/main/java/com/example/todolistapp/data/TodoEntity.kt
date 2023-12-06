package com.example.todolistapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todos")
data class TodoEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val description: String? = null,
    val completed: Boolean = false,
    val timestamp: Long = System.currentTimeMillis(), // Added field for timestamp
    val gifId: String? = null
)


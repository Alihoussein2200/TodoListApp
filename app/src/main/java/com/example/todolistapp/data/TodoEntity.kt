package com.example.todolistapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todos")
data class TodoEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val description: String? = null,
    val completed: Boolean = false,
    val timestamp: Long = System.currentTimeMillis(),
    val gifId: String? = null,
    val tags: String? = null // A comma-separated list of tags
)


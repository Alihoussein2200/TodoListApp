package com.example.todolistapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.todolistapp.ui.theme.TodoListAppTheme
import com.example.todolistapp.ui.todo_list.TodoScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint // This annotation is required for Hilt to work with activities
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TodoListAppTheme {
                TodoScreen() // Hilt will now handle providing the TodoViewModel
            }
        }
    }
}

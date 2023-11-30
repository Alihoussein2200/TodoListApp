package com.example.todolistapp.ui.todo_list

import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.todolistapp.viewmodel.TodoViewModel
import com.example.todolistapp.data.TodoEntity
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue


@Composable
fun TodoScreen() {
    // hiltViewModel() provides the ViewModel instance scoped to this composable
    val todoViewModel: TodoViewModel = hiltViewModel()
    val todos by todoViewModel.allTodos.observeAsState(emptyList())

    TodoList(
        todos = todos,
        onTodoCheckedChange = { todoViewModel.update(it) },
        onTodoClick = {
            // Navigate to detail screen or perform other actions
        }
    )

    TodoInputField(onTodoAdd = { title ->
        todoViewModel.insert(TodoEntity(title = title))
    })
}

package com.example.todolistapp.ui.todo_list

import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import com.example.todolistapp.viewmodel.TodoViewModel
import com.example.todolistapp.data.TodoEntity
import org.koin.androidx.compose.getViewModel

@Composable
fun TodoScreen(showGiphyPicker: (onGifSelected: (String) -> Unit) -> Unit) {
    val todoViewModel: TodoViewModel = getViewModel()
    val todos = todoViewModel.allTodos.observeAsState(listOf()).value

    TodoInputFieldWithGiphy(
        onTodoAdd = { text, gifId ->
            // Handle adding a new todo with optional GIF ID
            if (text.isNotBlank()) {
                todoViewModel.insert(TodoEntity(title = text, gifId = gifId))
            }
        },
        onGiphyClick = { showGiphyPicker { gifId ->
            // Handle the selected GIF ID
        }}
    )

    TodoList(
        todos = todos,
        onTodoCheckedChange = { todo -> todoViewModel.update(todo) },
        onTodoClick = { /* Implement if needed */ }
    )
}

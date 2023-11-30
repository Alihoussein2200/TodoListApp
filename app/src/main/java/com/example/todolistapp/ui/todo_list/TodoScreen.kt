package com.example.todolistapp.ui.todo_list

import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import com.example.todolistapp.viewmodel.TodoViewModel
import com.example.todolistapp.data.TodoEntity
import org.koin.androidx.compose.getViewModel

@Composable
fun TodoScreen() {
    val todoViewModel: TodoViewModel = getViewModel()
    val todos = todoViewModel.allTodos.observeAsState(listOf()).value

    TodoList(
        todos = todos,
        onTodoCheckedChange = { todo -> todoViewModel.update(todo) },
        onTodoClick = { /* Implement if needed */ }
    )

    TodoInputField(onTodoAdd = { title ->
        todoViewModel.insert(TodoEntity(title = title))
    })
}

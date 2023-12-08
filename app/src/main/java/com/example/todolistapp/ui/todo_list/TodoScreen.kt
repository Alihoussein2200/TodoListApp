package com.example.todolistapp.ui.todo_list

import androidx.compose.runtime.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.todolistapp.data.TodoEntity
import com.example.todolistapp.viewmodel.TodoViewModel
import org.koin.androidx.compose.getViewModel


@Composable
fun TodoScreen() {
    val todoViewModel: TodoViewModel = getViewModel()
    val todos by todoViewModel.filteredTodos.observeAsState(listOf())
    val scaffoldState = rememberScaffoldState()
    val (showEditDialog, setShowEditDialog) = remember { mutableStateOf(false) }
    val (editTodo, setEditTodo) = remember { mutableStateOf<TodoEntity?>(null) }
    val (showAddTask, setShowAddTask) = remember { mutableStateOf(false) }
    val errorMessage by todoViewModel.errorMessage.observeAsState("")

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { TopAppBar(title = { Text("My Todo List") }) },
        floatingActionButton = {
            FloatingActionButton(onClick = { setShowAddTask(true) }) {
                Icon(Icons.Filled.Add, contentDescription = "Add Task")
            }
        },
        floatingActionButtonPosition = FabPosition.End,
        content = { padding ->
            Column(modifier = Modifier.padding(padding)) {
                SearchBar(onSearch = todoViewModel::setSearchQuery)
                Filters(
                    onFilterStatus = todoViewModel::setFilterStatus,
                    onFilterTag = todoViewModel::setFilterTag
                )
                LazyColumn {
                    items(todos) { todo ->
                        TodoItem(
                            todo = todo,
                            onEdit = {
                                setEditTodo(todo)
                                setShowEditDialog(true)
                            },
                            onDelete = { todoViewModel.deleteTodo(it) },
                            onGiphySelect = {
                                // TODO: Show Giphy picker and handle GIF selection
                            }
                        )
                    }
                }
                if (showEditDialog && editTodo != null) {
                    EditTodoDialog(todo = editTodo, onDismiss = { setShowEditDialog(false) }, onSubmit = { updatedTodo ->
                        todoViewModel.updateTodo(updatedTodo)
                        setShowEditDialog(false)
                    })
                }
                if (errorMessage.isNotEmpty()) {
                    ErrorMessage(message = errorMessage, onDismiss = { todoViewModel.errorMessage.value = "" })
                }
                if (showAddTask) {
                    AddEditTask(onDismiss = { setShowAddTask(false) }, onSubmit = { newTodo ->
                        todoViewModel.insertTodo(newTodo)
                        setShowAddTask(false)
                    })
                }
            }
        }
    )
}

@Composable
fun AddEditTask(onDismiss: () -> Unit, onSubmit: (TodoEntity) -> Unit) {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var completed by remember { mutableStateOf(false) }
    var tags by remember { mutableStateOf("") }

    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp)) {
        TextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Title") }
        )
        TextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Description") }
        )
        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = completed,
                onCheckedChange = { completed = it }
            )
            Text(text = "Completed")
        }
        TextField(
            value = tags,
            onValueChange = { tags = it },
            label = { Text("Tags") }
        )
        Button(onClick = {
            onSubmit(TodoEntity(title = title, description = description, completed = completed, tags = tags))
            onDismiss()
        }) {
            Text("Add")
        }
    }
}
@Composable
fun EditTodoDialog(todo: TodoEntity, onDismiss: () -> Unit, onSubmit: (TodoEntity) -> Unit) {
    var title by remember { mutableStateOf(todo.title) }
    var description by remember { mutableStateOf(todo.description ?: "") }
    var completed by remember { mutableStateOf(todo.completed) }
    var tags by remember { mutableStateOf(todo.tags ?: "") }

    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = { Text(text = "Edit Todo") },
        text = {
            Column {
                TextField(
                    value = title,
                    onValueChange = { title = it },
                    label = { Text("Title") }
                )
                TextField(
                    value = description,
                    onValueChange = { description = it },
                    label = { Text("Description") }
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(
                        checked = completed,
                        onCheckedChange = { completed = it }
                    )
                    Text(text = "Completed")
                }
                TextField(
                    value = tags,
                    onValueChange = { tags = it },
                    label = { Text("Tags (comma-separated)") }
                )
            }
        },
        confirmButton = {
            TextButton(onClick = {
                onSubmit(todo.copy(title = title, description = description, completed = completed, tags = tags))
                onDismiss()
            }) {
                Text("Save")
            }
        },
        dismissButton = {
            TextButton(onClick = { onDismiss() }) {
                Text("Cancel")
            }
        }
    )
}

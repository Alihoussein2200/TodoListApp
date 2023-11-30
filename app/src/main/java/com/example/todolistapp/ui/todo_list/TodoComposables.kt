package com.example.todolistapp.ui.todo_list

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.OutlinedTextField // Correct import for OutlinedTextField
import androidx.compose.material3.Text // Correct import for Text
import androidx.compose.material3.Checkbox // Correct import for Checkbox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.example.todolistapp.data.TodoEntity
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController

@Composable
fun TodoList(todos: List<TodoEntity>, onTodoCheckedChange: (TodoEntity) -> Unit, onTodoClick: (TodoEntity) -> Unit) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(all = 8.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        items(todos) { todo ->
            TodoItem(
                todo = todo,
                onTodoCheckedChange = onTodoCheckedChange,
                onTodoClick = onTodoClick
            )
        }
    }
}

@Composable
fun TodoItem(todo: TodoEntity, onTodoCheckedChange: (TodoEntity) -> Unit, onTodoClick: (TodoEntity) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        elevation = 2.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = todo.completed,
                onCheckedChange = { isChecked ->
                    onTodoCheckedChange(todo.copy(completed = isChecked))
                }
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = todo.title,
                modifier = Modifier.weight(1f)
            )
            IconButton(onClick = { onTodoClick(todo) }) {
                Icon(Icons.Default.Delete, contentDescription = "Delete")
            }
        }
    }
}

@Composable
fun TodoInputField(onTodoAdd: (String) -> Unit) {
    val (text, setText) = remember { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current

    OutlinedTextField(
        value = text,
        onValueChange = setText,
        label = { Text("Add a task") },
        singleLine = true,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        keyboardActions = KeyboardActions(
            onDone = {
                onTodoAdd(text)
                setText("")
                keyboardController?.hide()
            }
        ),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color.Blue,
            unfocusedBorderColor = Color.LightGray
        ),
        imeAction = ImeAction.Done
    )
}

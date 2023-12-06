package com.example.todolistapp.ui.todo_list

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.Checkbox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.example.todolistapp.data.TodoEntity
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.ui.res.painterResource


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
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp) // Using CardDefaults for elevation
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
    var text by remember { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current

    OutlinedTextField(
        value = text,
        onValueChange = { newText -> text = newText },
        label = { Text("Add a task", style = MaterialTheme.typography.bodyMedium) },
        placeholder = { Text("Enter todo item", style = MaterialTheme.typography.bodySmall) },
        singleLine = true,
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = MaterialTheme.colorScheme.primary,
            unfocusedBorderColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3f),
            cursorColor = MaterialTheme.colorScheme.primary
        ),
        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions(
            onDone = {
                if (text.isNotBlank()) {
                    onTodoAdd(text)
                    text = "" // Clear the text field
                    keyboardController?.hide() // Hide the keyboard
                }
            }
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    )
}
@Composable
fun TodoInputFieldWithGiphy(
    onTodoAdd: (String, String?) -> Unit,
    onGiphyClick: () -> Unit
) {
    var text by remember { mutableStateOf("") }
    var selectedGifId by remember { mutableStateOf<String?>(null) }

    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp), // Add padding to separate items from screen edges
            verticalAlignment = Alignment.CenterVertically // Vertically align the components in the Row
        ) {
            // Give the TextField a weight so it fills the space left by the IconButton
            TextField(
                value = text,
                onValueChange = { text = it },
                label = { Text("Add a task") },
                modifier = Modifier.weight(1f) // TextField will fill the remaining space
            )
            IconButton(onClick = onGiphyClick) {
                Icon(painter = painterResource(id = com.giphy.sdk.ui.R.drawable.gph_ic_gif), contentDescription = "Giphy")
            }
        }

        // Button is now within the Column, so it naturally falls below the Row
        Button(
            onClick = {
                if (text.isNotBlank()) {
                    onTodoAdd(text, selectedGifId)
                    text = "" // Reset the text field
                    selectedGifId = null // Reset the selected GIF ID
                }
            },
            modifier = Modifier
                .padding(8.dp) // Add padding for spacing
        ) {
            Text("Add")
        }
    }
}

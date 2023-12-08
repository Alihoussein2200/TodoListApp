package com.example.todolistapp.ui.todo_list

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.example.todolistapp.data.TodoEntity



@Composable
fun TodoItem(todo: TodoEntity, onEdit: (TodoEntity) -> Unit, onDelete: (TodoEntity) -> Unit, onGiphySelect: () -> Unit) {
    Card(
        backgroundColor = if (todo.completed) Color.LightGray else Color.White,
        modifier = Modifier.fillMaxWidth().padding(8.dp)
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Text(text = "Title: ${todo.title}", style = MaterialTheme.typography.h6)
            Text(text = "Description: ${todo.description}", style = MaterialTheme.typography.body1)
            Text(text = "Status: ${if (todo.completed) "Completed" else "Incomplete"}", style = MaterialTheme.typography.body2)
            Text(text = "Tags: ${todo.tags}", style = MaterialTheme.typography.body2)
            // Display the GIF using the GIF ID - Placeholder for actual implementation
            Button(onClick = { onEdit(todo) }) { Text("Edit") }
            Button(onClick = { onDelete(todo) }) { Text("Delete") }
            Button(onClick = { onGiphySelect() }) { Text("Select GIF") }
        }
    }
}

@Composable
fun SearchBar(onSearch: (String) -> Unit) {
    var text by remember { mutableStateOf("") }

    OutlinedTextField(
        value = text,
        onValueChange = { text = it },
        label = { Text("Search Todos") },
        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
        modifier = Modifier.fillMaxWidth().padding(8.dp),
        trailingIcon = {
            IconButton(onClick = { onSearch(text) }) {
                Icon(Icons.Filled.Search, contentDescription = "Search")
            }
        }
    )
}

@Composable
fun Filters(onFilterStatus: (Boolean?) -> Unit, onFilterTag: (String?) -> Unit) {
    Column(modifier = Modifier.padding(8.dp)) {
        Text("Filter by:", style = MaterialTheme.typography.subtitle1)

        // Filter by Completion Status
        var selectedStatus by remember { mutableStateOf<Boolean?>(null) }
        Row(verticalAlignment = Alignment.CenterVertically) {
            RadioButton(selected = selectedStatus == true, onClick = { selectedStatus = true })
            Text("Completed", style = MaterialTheme.typography.body1)
            Spacer(modifier = Modifier.width(8.dp))
            RadioButton(selected = selectedStatus == false, onClick = { selectedStatus = false })
            Text("Incomplete", style = MaterialTheme.typography.body1)
        }
        Button(onClick = { onFilterStatus(selectedStatus) }) {
            Text("Apply Status Filter")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Filter by Tags
        var tagText by remember { mutableStateOf("") }
        OutlinedTextField(
            value = tagText,
            onValueChange = { tagText = it },
            label = { Text("Filter by Tag") },
            modifier = Modifier.fillMaxWidth()
        )
        Button(onClick = { onFilterTag(tagText) }) {
            Text("Apply Tag Filter")
        }
    }
}

@Composable
fun GiphyPicker(onGifSelected: (String) -> Unit) {
    // Detailed implementation of Giphy picker
    // actual logic for selecting a GIF from Giphy API
    // For example, a dialog with a list of GIFs to choose from
}

@Composable
fun ErrorMessage(message: String, onDismiss: () -> Unit) {
    if (message.isNotEmpty()) {
        Snackbar(
            action = {
                Button(onClick = onDismiss) { Text("Dismiss") }
            }
        ) {
            Text(text = message)
        }
    }
}

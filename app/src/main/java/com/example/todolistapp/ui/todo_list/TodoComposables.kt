package com.example.todolistapp.ui.todo_list

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField // Correct import for OutlinedTextField
import androidx.compose.material3.Text // Correct import for Text
import androidx.compose.material3.Checkbox // Correct import for Checkbox
import androidx.compose.material3.MaterialTheme // Correct import for MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue // Correct import for Delegates
import androidx.compose.runtime.setValue // Correct import for Delegates
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.example.todolistapp.data.TodoEntity
import androidx.compose.ui.text.input.TextFieldValue


@Composable
fun TodoList(
    todos: List<TodoEntity>,
    onTodoCheckedChange: (TodoEntity) -> Unit,
    onTodoClick: (TodoEntity) -> Unit
) {
    LazyColumn {
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
fun TodoItem(
    todo: TodoEntity,
    onTodoCheckedChange: (TodoEntity) -> Unit,
    onTodoClick: (TodoEntity) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = todo.title,
            style = MaterialTheme.typography.bodyMedium
        )
        Checkbox(
            checked = todo.completed,
            onCheckedChange = { isChecked ->
                onTodoCheckedChange(todo.copy(completed = isChecked)) // 'it' replaced with 'isChecked'
            }
        )
    }
    // Consider adding a clickable modifier to handle onTodoClick if needed
}

@Composable
fun TodoInputField(onTodoAdd: (String) -> Unit) {
    var text by remember { mutableStateOf(TextFieldValue("")) }

    OutlinedTextField(
        value = text,
        onValueChange = { newText -> text = newText },
        label = { Text("Add a task") },
        singleLine = true,
        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions(
            onDone = {
                if (text.text.isNotBlank()) {
                    onTodoAdd(text.text)
                    text = TextFieldValue("") // Clear the input field after adding
                }
            }
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    )
}
package com.example.widgets

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun Button_Text_TextField() {
    var count by remember {
        mutableStateOf(1)
    }
    var text by remember {
        mutableStateOf("")
    }
    Text(
        text = "Count: $count", fontSize = 30.sp,
        fontWeight = FontWeight.Bold
    )
    Button(onClick = { count += 1 }) {
        Text(text = "Count++")
    }
    TextField(value = text, onValueChange = { text = it })
    Text(text = text)
}
package com.example.widgets

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun ProgressIndicatorUsage() {
    var isLoading by remember {
        mutableStateOf(false)
    }
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(text = if (isLoading) "Close" else "Open", Modifier.absolutePadding(right = 10.dp))
        Switch(checked = isLoading, onCheckedChange = { isLoading = it })
    }
    if (isLoading) CircularProgressIndicator() else Box(modifier = Modifier)
}

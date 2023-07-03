package com.example.widgets

import androidx.compose.material.Switch
import androidx.compose.material.SwitchDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color

@Composable
fun SwitchUsage() {
    var isChecked by remember {
        mutableStateOf(false)
    }
    Switch(
        checked = isChecked, onCheckedChange = { isChecked = it },
        colors = SwitchDefaults.colors(
            checkedThumbColor = Color.Red,//Pin Rengi
            uncheckedThumbColor = Color.Gray,
            checkedTrackColor = Color.Red,//Pin Arkasındaki renk
            uncheckedTrackColor = Color.Gray,
        )
    )
}
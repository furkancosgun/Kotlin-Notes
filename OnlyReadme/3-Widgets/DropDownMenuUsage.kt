package com.example.widgets

import androidx.compose.foundation.layout.Box
import androidx.compose.material.Button
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Text
import androidx.compose.runtime.*

@Composable
fun DropDownMenuUsage() {
    var isOpenedMenu by remember {
        mutableStateOf(false)
    }
    var selectedCity by remember {
        mutableStateOf("")
    }
    val cities = mutableListOf("Istanbul", "Ankara", "Nevsehir", "Izmır")
    Text(text = "Selected City:$selectedCity")
    Box {
        Button(onClick = { isOpenedMenu = true }) {
            Text(text = "Select City")
        }
        DropdownMenu(expanded = isOpenedMenu, onDismissRequest = { isOpenedMenu = false }) {
            cities.forEachIndexed { index: Int, value: String ->
                DropdownMenuItem(onClick = {
                    selectedCity = value
                    isOpenedMenu = false
                }) {
                    Text(text = value)
                }
            }
        }
    }
}

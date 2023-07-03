package com.example.widgets

import androidx.compose.material.Slider
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp


@Composable
fun SliderUsage() {
    var sliderValue by remember {
        mutableStateOf(0f)
    }

    Slider(
        value = sliderValue, onValueChange = { sliderValue = it },
        valueRange = 0f..100f,
    )
    Text(
        text = "Slider Value: $sliderValue",
        fontSize = 25.sp,
        fontWeight = FontWeight.Bold
    )
}
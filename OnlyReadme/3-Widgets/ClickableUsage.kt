package com.example.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ClickableUsage() {
    Box(contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(300.dp)
            .background(Color.Red)
            .clickable {
                println("BOX Clicked")
            }
    ) {
        Text(text = "Clickable BOX", color = Color.White, fontSize = 30.sp)
    }
}
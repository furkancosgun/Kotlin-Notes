package com.example.widgets

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun RadioButtonUsage() {
    var selectedIndex by remember {
        mutableStateOf(0)
    }
    val cities = mutableListOf<String>("Nevsehir", "Istanbul", "Ankara", "Izmir")

    cities.forEachIndexed { index, city ->
        Row(
            Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            RadioButton(
                selected = index == selectedIndex,
                onClick = {
                    selectedIndex = index
                    println("Selected Index:$index")
                })
            Text(text = city, fontSize = 25.sp, fontWeight = FontWeight.Bold)
        }
    }
}

package com.example.widgets

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExtendedFloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource


@Composable
fun HomeScreen() {
    /*
    FAB Kullanmak için Scaffold tarafıdnan sarmalamak lazım
    Content uzerinde gozukur
     */
    Scaffold(floatingActionButton = {
        /*FloatingActionButton(onClick = {
            println("FAB Clicked")
        }, backgroundColor = Color.Red) {
            Icon(
                painter = painterResource(id = R.drawable.fav),
                contentDescription = "Fav Icon",
                tint = Color.White
            )
        }
         */
        ExtendedFloatingActionButton(backgroundColor = Color.White,
            text = { Text(text = "HELLO FAB") },
            onClick = { println("FAB Clicked") }, icon = {
                Icon(
                    painter = painterResource(id = R.drawable.fav),
                    contentDescription = "Fav Icon",
                    tint = Color.Red
                )
            })
    }) {
        Column(Modifier.fillMaxSize()) {
            Text(text = it.toString())
        }
    }
}

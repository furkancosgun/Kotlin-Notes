package com.example.mvvm.Classic

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@OptIn(ExperimentalMaterial3Api::class)
@Preview(showSystemUi = true)
@Composable
fun ClassicView() {
    var result by remember {
        mutableStateOf(0)
    }
    var firstnumber by remember {
        mutableStateOf("")
    }
    var secondnumber by remember {
        mutableStateOf("")
    }
    Scaffold {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(text = "$result", fontWeight = FontWeight.Bold, fontSize = 32.sp)

                TextField(value = firstnumber, onValueChange = { t -> firstnumber = t }, label = {
                    Text(text = "First Number")
                }, keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                )
                )
                TextField(value = secondnumber, onValueChange = { t -> secondnumber = t }, label = {
                    Text(text = "Second Number")
                }, keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                )
                )
                Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    Button(onClick = {
                        result = (firstnumber.toInt() + secondnumber.toInt())
                    }) {
                        Text(text = "+")
                    }
                    Button(onClick = {
                        result = (firstnumber.toInt() - secondnumber.toInt())
                    }) {
                        Text(text = "-")
                    }
                    Button(onClick = { result = (firstnumber.toInt() * secondnumber.toInt()) }) {
                        Text(text = "*")
                    }
                    Button(onClick = { result = (firstnumber.toInt() / secondnumber.toInt()) }) {
                        Text(text = "/")
                    }
                }
            }
        }
    }
}



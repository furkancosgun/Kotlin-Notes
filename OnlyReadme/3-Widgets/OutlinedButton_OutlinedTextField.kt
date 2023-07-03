package com.example.widgets

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.sp

@Composable
fun OutlinedButton_OutlinedTextField() {
    var count by remember {
        mutableStateOf(0)
    }
    var text by remember {
        mutableStateOf("")
    }

    OutlinedTextField(
        value = text, onValueChange = { text = it },
        placeholder = {
            Text(text = "Here Is PlaceHolder")
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            cursorColor = Color.Red,
            textColor = Color.Red,
            focusedBorderColor = Color.Red
        ),
        visualTransformation = PasswordVisualTransformation(),//Yazılan Metnin Gorunum Tipi  ,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),//Klavye Tipi Numeric eposta şifre vb.

    )
    OutlinedButton(
        modifier = Modifier.fillMaxWidth(),
        onClick = {
            count++
            text = count.toString()
        },
        colors = ButtonDefaults.outlinedButtonColors(
            backgroundColor = Color.Red,
            contentColor = Color.White
        )
    ) {
        Text(text = "+", fontSize = 20.sp, fontWeight = FontWeight.Bold)
    }
    OutlinedButton(
        modifier = Modifier.fillMaxWidth(),
        onClick = {
            count--
            text = count.toString()
        },
        colors = ButtonDefaults.outlinedButtonColors(
            backgroundColor = Color.Red,
            contentColor = Color.White
        )
    ) {
        Text(text = "-", fontSize = 20.sp, fontWeight = FontWeight.Bold)
    }

}

package com.example.jetpackcomposetasarim

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpackcomposetasarim.ui.theme.JetpackComposeTasarimTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeTasarimTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    //Greeting("Android")
                    //IlhamVerAppUI()
                    LoginAppUI()
                }
            }
        }
    }
}


@Composable
fun LoginAppUI() {
    var email by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }
    Column(
        Modifier
            .fillMaxSize()
            .background(Color.Blue)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(3f)
                .padding(16.dp)
        ) {
            Text(
                modifier = Modifier.align(Alignment.BottomStart),
                text = "Hello Again,\nSign In!",
                fontSize = 64.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
            )
        }
        Spacer(modifier = Modifier.weight(2f))
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .weight(5f)
                .fillMaxWidth()
                .padding(16.dp)

        ) {
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White), value = email,
                onValueChange = {
                    email = it
                },
                placeholder = {
                    Text(text = "Email")
                }
            )
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White), value = password,
                onValueChange = {
                    password = it
                },
                placeholder = {
                    Text(text = "Password")
                }
            )

            Button(
                onClick = { println("Email: $email , Password: $password") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.outlinedButtonColors()
            ) {
                Text(text = "Sign In", fontSize = 30.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    JetpackComposeTasarimTheme {
        //SecondStep()
        //IlhamVerAppUI()
        LoginAppUI()
    }
}
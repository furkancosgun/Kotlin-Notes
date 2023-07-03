package com.example.calismayapisi.SayfalarArasiGecis

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun ThirdScreen(navController: NavController) {
    var userId by remember {
        mutableStateOf("")
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Third Screen", fontSize = 30.sp)
        Spacer(modifier = Modifier.height(100.dp))
        Button(modifier = Modifier.fillMaxWidth(), onClick = {
            navController.popBackStack()
        }) {
            Text(text = "Go To Back Screen")
        }

        //Userid belirleyecegimiz palan
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = userId,
            onValueChange = { userId = it }, placeholder = {
                Text(text = "UserId..")
            }
        )

        Button(modifier = Modifier.fillMaxWidth(), onClick = {
            //İlk Path Olarak Bekledgimiz Idye şimdi parametre vererek geçiş saglıyoruz
            navController.navigate("LastScreen/${userId.toInt()}")
        }) {
            Text(text = "Go To Last Screen With Param")
        }
    }
}

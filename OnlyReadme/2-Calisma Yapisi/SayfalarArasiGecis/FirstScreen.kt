package com.example.calismayapisi.SayfalarArasiGecis

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun FirstScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "First Screen", fontSize = 30.sp)
        Spacer(modifier = Modifier.height(100.dp))
        Button(modifier = Modifier.fillMaxWidth(), onClick = {
            navController.navigate("SecondScreen")
        }) {
            Text(text = "Go To Second Screen")
        }
    }
}
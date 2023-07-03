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
fun SecondScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Second Screen", fontSize = 30.sp)
        Spacer(modifier = Modifier.height(100.dp))
        Button(modifier = Modifier.fillMaxWidth(), onClick = {
            navController.popBackStack()
        }) {
            Text(text = "Go To Back Screen")
        }
        Button(modifier = Modifier.fillMaxWidth(), onClick = {
            navController.navigate("ThirdScreen") {
                popUpTo("SecondScreen") {
                    inclusive = true
                }
            }
        }) {
            Text(text = "Go To Third Screen And Delete This Screen From Backstack")
        }
        Button(modifier = Modifier.fillMaxWidth(), onClick = {
            navController.navigate("ThirdScreen")
        }) {
            Text(text = "Go To Third Screen")
        }
    }
}

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
import com.example.calismayapisi.Model.User
import com.google.gson.Gson

@Composable
fun ScreenWithParam(navController: NavController, userId: Int) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Last Screen userId:$userId", fontSize = 30.sp)
        Spacer(modifier = Modifier.height(100.dp))
        Button(modifier = Modifier.fillMaxWidth(), onClick = {
            navController.popBackStack()
        }) {
            Text(text = "Go to Back Screen")
        }

        Button(modifier = Modifier.fillMaxWidth(), onClick = {
            val userModel = User("Furkan", "Cosgun", 20)
            //Model Olan Degerimizi Gson Kutuphanesi Sayesinde STring Yapısına Donuşturerek
            //Sayfa Geçişi Yapıyoruz
            val strUser = Gson().toJson(userModel)
            navController.navigate("ScreenWithModel/$strUser")
        }) {
            Text(text = "Go to Model Screen")
        }
    }
}
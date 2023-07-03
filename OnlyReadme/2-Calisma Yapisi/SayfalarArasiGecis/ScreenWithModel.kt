package com.example.calismayapisi.SayfalarArasiGecis

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.calismayapisi.Model.User

@Composable
fun ScreenWithModel(navController: NavController, user: User) {
    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = user.name)
        Text(text = user.surename)
        Text(text = user.age.toString())
        Button(onClick = {
            navController.popBackStack()
        }) {
            Text(text = "Go To Back Screen")
        }
    }
}
package com.example.jetpackcomposetasarim

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ColoredBox(color: Color, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .width(100.dp)
            .height(100.dp)
            .background(color)
    )//Boş Bir View Oluştrur
}

//Box Column Row Allignment Arragment Kullanımı
@Composable
fun FirstStep(name: String) {
    Column(modifier = Modifier.fillMaxSize()) {//Dikey Hizalama Yapar
        ColoredBox(color = Color.Red)
        ColoredBox(color = Color.Green)
        ColoredBox(color = Color.Blue)
        Row(Modifier.fillMaxWidth()) {//Yatay Hizalama Yapar
            ColoredBox(color = Color.Red)
            ColoredBox(color = Color.Green)
            ColoredBox(color = Color.Blue)
        }
        Row(
            verticalAlignment = Alignment.Bottom,//Dikey Konumlandırma
            horizontalArrangement = Arrangement.Center,//Yatay Konumlandırma
            modifier = Modifier.fillMaxWidth()
        ) {
            ColoredBox(color = Color.Magenta)
        }
        Column(
            Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Bottom,//Dikey Konumlandırma
            horizontalAlignment = Alignment.CenterHorizontally//Yatay Konumlandırma
        ) {
            ColoredBox(color = Color.Yellow)
        }
    }

}
package com.example.jetpackcomposetasarim

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun SecondStep() {
    Column(Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .size(200.dp)
                .background(Color.Blue)
        ) {
            Text(
                text = "Hello Android",
                color = Color.White,
                fontSize = 30.sp,
                modifier = Modifier.align(//İçinde Bulundugu Duruma Gore Kendini Hizalar
                    Alignment.BottomCenter
                )
            )
        }
        Spacer(modifier = Modifier.height(100.dp))//Boş Bir Şekilde Gozukerek Viewlar Arasına Boşluk Bırakabilir
        Text(
            text = "Yukarıda 100dp Boşluk Var",
            Modifier
                .padding(10.dp)//Bütün Koşelere Eşit bir Şekilde Padding verir
                .absolutePadding(
                    10.dp,
                    20.dp,
                    30.dp,
                    40.dp
                )//Bütün Koşelere Farklı Farklı Paddingler verebiliriz.
        )

        Row(Modifier.fillMaxWidth()) {
            //Weight Kullanarak Ekranımızı Olçeklendirebilirz %10-%20 kullan şeklinde
            ColoredBox(color = Color.Red, Modifier.weight(50f))//Ekranın %50 sini kullanıyor
            ColoredBox(color = Color.Green, Modifier.weight(10f))
            ColoredBox(color = Color.Blue, Modifier.weight(40f))
        }
        Text(text = stringResource(id = R.string.app_name))//Şeklinde Sisteme Gömülen Metni Alabilirz

        //Button Kullanımı
        Button(
            onClick = { println("Button Pressed") },
            colors = ButtonDefaults.buttonColors(//Buttonu ozelleştirmek
                backgroundColor = Color.Red,
                contentColor = Color.White
            )
        ) {
            Text(text = "Click Me!")
        }
    }
}
/*
    **Çoklu Dil Destegi Eklemek İçin**
    * RES->values-tr ->Eğer Telefon Dili Türkçe İse Çalışcak
    * RES->values-fr ->Eğer Telefon Dili Fransıca ise Çalışcak
    * Şeklinde Ayarlayabilirz
 */


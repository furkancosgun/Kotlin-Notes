package com.example.calismayapisi

import android.app.Activity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.calismayapisi.SayfalarArasiGecis.ScreenController
import com.example.calismayapisi.ui.theme.CalismaYapisiTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalismaYapisiTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    //StatefulWidget()
                    ScreenController()


                    //Acitivtyler Uzerinde İşlem Yapmak İçin Bu şekilde Oluşturabilirz
                    val activity = (LocalContext.current) as Activity
                    //Geri Tuşuna Basıldıgı Anda Tetiklenir
                    BackHandler {
                        Toast.makeText(
                            applicationContext,
                            "Geri Tuşuna Basıldı",
                            Toast.LENGTH_LONG
                        ).show()

                        activity.finish()//Uygulamayı Sonlandırır
                    }
                    /*Yaşam Donguleri
                        LaunchedEffect()
                        Başka Sayfadan Geldiginde Veya Ekran İlk Kez Açıldıgında 1 defa çalışır



                        SideEffect {}
                        Ekranın Her State Degişimi Oldugunda Veya ilk GOruntulemelerde


                        DisposableEffect()
                        Sayfadan Ayrıldıgımızda Çalışır
                     */

                    LaunchedEffect(key1 = true) {
                        println("Launched Effect Worked")
                    }
                    SideEffect {
                        println("SideEffect Worked")
                    }
                    DisposableEffect(Unit) {
                        onDispose {
                            println("DisposableEffect Worked")
                        }
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    CalismaYapisiTheme {
        //StatefulWidget()
        ScreenController()
    }
}
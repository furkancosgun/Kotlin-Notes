package com.furkancosgun.diceeapp

import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.furkancosgun.diceeapp.ui.theme.DiceeAppTheme
import java.util.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DiceeAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                  MainScreen()
                }
            }
        }
    }
}

@Composable
fun MainScreen(){
    //State tanımlana remmebr ile yapılır ki bu da tekrar render oldugunda uyhulamanın degiskenimizi
    //hatırlamasını saglar
    var randomNumber  by remember {
        mutableStateOf(1)
    }

    //Resource Imagları listeleme
    val diceeResource = mutableListOf<Int>(
        R.drawable.dice_1,
        R.drawable.dice_2,
        R.drawable.dice_3,
        R.drawable.dice_4,
        R.drawable.dice_5,
        R.drawable.dice_6)
    
    Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {

        //Resource içinden ıamge alırken painter kullanılır
        Image(painter = painterResource(id = diceeResource[randomNumber]), contentDescription = "Random Dice")
        Button(onClick = {
            randomNumber = Random().nextInt(6)
            println("DEBUG: $randomNumber")
        }
        ) {
            Text(text = "Random It!")
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    DiceeAppTheme {
      MainScreen()
    }
}
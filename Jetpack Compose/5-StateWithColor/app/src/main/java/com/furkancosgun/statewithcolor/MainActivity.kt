package com.furkancosgun.statewithcolor

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.furkancosgun.statewithcolor.ui.theme.StateWithColorTheme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val updateColor = remember {
                mutableStateOf(Color.Red)
            }
            StateWithColorTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MainScren(updateColor.value){
                        //Color dondugunde bizim stateti olan colora geçer ve gerekli yerler tekrardan render edilir
                        updateColor.value = it
                    }
                }
            }
        }
    }
}a

//Screen uzerine bir color gondeririz
//bir de color donderen fonksyon gondeririz
@Composable
fun MainScren(color : Color,updateColor : (Color)->Unit) {

    Box(modifier = Modifier
        .fillMaxSize()
        .background(color)
        .clickable {//Tıklandıgında random bir renk ueretir ve donderir
            updateColor(Color(Random.nextFloat(), Random.nextFloat(), Random.nextFloat(), 1f))
        })
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    StateWithColorTheme {
        val updateColor = remember {
            mutableStateOf(Color.Red)
        }
        Column(modifier = Modifier.fillMaxSize()) {
            MainScren(updateColor.value){
                updateColor.value = it
            }
            MainScren(updateColor.value){
                updateColor.value = it
            }
        }
    }
}
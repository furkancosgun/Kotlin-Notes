package com.furkancosgun.cardimage

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.furkancosgun.cardimage.ui.theme.CardImageTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CardImageTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting(painterResource(id = R.drawable.duck), imageContent = "Duck", title = "Hello Duck")
                }
            }
        }
    }
}

@Composable
fun Greeting(
    image:Painter,
    imageContent:String,
    title:String,
) {
    Column() {
        Card(
            shape = RoundedCornerShape(10),//Corner Radius
            elevation = 100.dp,
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(0.5f)//Max Ekran genişliginin yarısı
        ) {
            Box(modifier = Modifier.height(200.dp).background(//Arkaplana Gradient verme
                Brush.verticalGradient(
                    colors = listOf(Color.White,Color.Black),//Renkler
                    startY = 300f//Başlangıc noktası
                )
            ),){
                Image(painter = image, contentDescription = imageContent)//Image Ekleme
                Box(modifier = Modifier//Image altına textk eklencek onu tutması için tekrar box
                    .fillMaxSize()
                    .padding(16.dp),
                    contentAlignment = Alignment.BottomStart,//Içerik hizalama
                ){
                    Text(
                        text = title,
                        textAlign = TextAlign.Center,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        style = TextStyle(color = Color.White)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    CardImageTheme {
        Greeting(painterResource(id = R.drawable.duck), imageContent = "Duck", title = "Hello Duck")
    }
}
package com.furkancosgun.simpleanimations

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.furkancosgun.simpleanimations.ui.theme.SimpleAnimationsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SimpleAnimationsTheme {
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
    val animSize = remember{
        mutableStateOf(100)
    }

    //DP Anim
   /**
    * @see Sadece_Boyutlandırma_yapar
    val animWithSize = animateDpAsState(
        targetValue = animSize.value.dp
    )
    */

    val animWithSize = animateDpAsState(
        targetValue = animSize.value.dp,
       spring(//Sekme Tarzinda Animasyon
          // Spring.DampingRatioHighBouncy //Sekme hissdilir
          // Spring.DampingRatioLowBouncy //Sekme hissedilmez
          // Spring.DampingRatioNoBouncy //Sekme Olmaz
          // Spring.DampingRatioMediumBouncy //Orta Derce Sekme
       Spring.DampingRatioHighBouncy
       )
       /**
        * @see verilen_zaman_arasında_yapar
        tween(//Sure İçinde İşlemi Bitirir
            durationMillis = 1000,//Sure
            delayMillis = 900,//Bekleme suresi
            easing = FastOutLinearInEasing //Animasyon tipi
        )
        */
    )

    //Color Animation
    val infiniteTransition = rememberInfiniteTransition()//Sonsuz işlem yapması için state

    val colorAnimation = infiniteTransition.animateColor(//Surekli renk degişimi için renk paleti
        initialValue = Color.Red,//Başlangıc rengi
        targetValue = Color.Blue,//Hedef Renk

        animationSpec = infiniteRepeatable(//sonsuz tekrar olcak şeklinde
            tween(durationMillis = 3000),//3 saniye
            repeatMode = RepeatMode.Reverse//Başladıgı renge geri doner
        )
    )
    Column {
        Box(modifier = Modifier
            .padding(10.dp)
            .background(colorAnimation.value)
            .size(animWithSize.value),
            Alignment.Center){
            Button(onClick = { animSize.value += 50 }) {
                Text(text = "Click Me")
            }
        }

    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    SimpleAnimationsTheme {
        MainScreen()
    }
}
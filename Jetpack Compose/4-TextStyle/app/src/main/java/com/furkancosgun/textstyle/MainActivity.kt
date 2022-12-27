package com.furkancosgun.textstyle

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.furkancosgun.textstyle.ui.theme.TextStyleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val fontFamily = FontFamily(
            Font(R.font.lexend_regular, FontWeight.Bold)
        )
        setContent {
        Greeting(name = "Lorem Ipsum Qazwsxedcrfv", fontFamily = fontFamily)
        }
    }
}

@Composable
fun Greeting(name: String,fontFamily: FontFamily) {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.White))
    {
        Text(text = buildAnnotatedString {//Text oluşturulur ve karaktere veya kelimeye gore font biçimlendirlir
            withStyle(
                style = SpanStyle(fontWeight = FontWeight.Bold, fontSize = 30.sp)
            ){
                append(name.first())//name degerinin ilk karakteri farklı stilde
            }
            append(name.substring(1,name.length))//Geriye kalanı aşagıdaki stilde
        },
            fontSize = 16.sp,
            fontFamily = fontFamily,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center,
            textDecoration = TextDecoration.Underline // Altı çizili
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    val fontFamily = FontFamily(
        Font(R.font.lexend_regular, FontWeight.Bold)
    )
    TextStyleTheme {
        Greeting("Android", fontFamily = fontFamily)
    }
}
package com.furkancosgun.lists

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.furkancosgun.lists.ui.theme.ListsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ListsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                secondList()
                }
            }
        }
    }
}

@Composable
fun firstList(){
    val verticalScrollState = rememberScrollState()
 Column(Modifier.verticalScroll(verticalScrollState)) {
     for(i in 1 until 100){
        Text(
            text = "With Column index:$i",
            style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 30.sp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )
     }
 }
}

@Composable
fun secondList(){
    val verticalScrollState = rememberScrollState()
    LazyColumn() {
        for(i in 1 until 100){
           item {
               Text(
                   text = "With Column index:$i",
                   style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 30.sp),
                   modifier = Modifier
                       .fillMaxWidth()
                       .padding(vertical = 8.dp)
               )
           }
        }
    }
}


@Composable
fun thirdList(){
    val verticalScrollState = rememberScrollState()
    LazyColumn() {
            items(100) {
                Text(
                    text = "With Column index:$it",
                    style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 30.sp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                )
            }
    }
}

@Composable
fun fourthList(){
    val verticalScrollState = rememberScrollState()
    LazyColumn() {

        itemsIndexed(
            listOf("Kotlin","Jetpack","Compose","With","Java","Wirtual","Machine")
        ){ index,text ->
            Text(
                text = "With Column index:$index and Text: $text",
                style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 20.sp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            )
        }
    }
}





@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    ListsTheme {
      fourthList()
    }
}
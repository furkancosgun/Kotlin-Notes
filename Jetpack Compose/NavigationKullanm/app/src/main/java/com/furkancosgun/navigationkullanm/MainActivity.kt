package com.furkancosgun.navigationkullanm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.furkancosgun.navigationkullanm.ui.theme.NavigationKullanımıTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NavigationKullanımıTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Navigation()
                }
            }
        }
    }
}

@Composable
fun Navigation() {
    /*
    def nav_version = "2.5.3"
    implementation("androidx.navigation:navigation-compose:$nav_version")
    Module Build Gradle Altına Eklenir
     */
    val navController = rememberNavController()//Sayfalar arası geçiş için kullanılır

    //Navhost bizim butun routeları tutar ve yonlendirir
    NavHost(navController = navController, startDestination = Screen.MainScreen.route){
        composable(route = Screen.MainScreen.route){//Composable objesini kullanarak hangi routta hangi ekranı kullancagımızı soyleriz
            MainScreen(navController = navController)//Ekrandan ekrana geçiş saglayacagımız durumalrda o ekranlara parametre gondeririz
        }
        composable(
            route = Screen.DetailScreen.route + "/{name}",//Parametre gondererek geçiş saglamak için degerler bu şekilde alınır
            arguments = listOf(//Argumanlar bir liste veya bir url degğişkeni gibi gonderilir
                navArgument("name"){//Ve parametre adı route pathı ile aynı olması gerekir
                    type = NavType.StringType//Gonderilcek veri tipi
                    defaultValue = ""//Varsayılan degeri
                    nullable = true//Null olma durumur
                }
            )
        ){
            DetailScreen(name = it.arguments?.getString("name"))//Ve bu ekran gelirken parametresi ile birlikte alınarak gosterilir
        }
    }
}

@Composable
fun MainScreen(navController: NavController) {
    var text by remember {//Text Field için state durumu olan string
        mutableStateOf("")
    }
    Box(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)){
        Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
            OutlinedTextField(value = text, onValueChange = { it-> text = it },)
            Button(onClick = {
                //Bu sayafaya rout yapısından aldıgımız navcontrollerı kullanarak diger sayfaya geçiş saglarız
                //ve parametreyle beraber gideriz
                navController.navigate(Screen.DetailScreen.withArgs(text))
            }) {
                Text(text = "Go To Detail Page With Arg")
            }
        }
    }
}

@Composable
fun DetailScreen(name : String?) {//Gelen parametreyi almak için fonk parametresi gibi istememiz gerekir
    Box(modifier = Modifier.fillMaxSize()){
        Text(text = "$name")
    }
}



@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    NavigationKullanımıTheme {
        Navigation()
    }
}
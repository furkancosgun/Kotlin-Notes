package com.example.materialdesign

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument



/*
def nav_version = "2.6.0"

implementation("androidx.navigation:navigation-compose:$nav_version")
*/
/*
    Sayfalar Arası Geçişlerimizi Yönetmek İçin Aşağıdaki Yapıyı Kullanıyourz
 */
@Composable
fun NavigationController() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "list") {
        composable("list") {
            ListScreen(navController = navController)/* navController.navigate("route") şeklinde başka sayfaya geçeriz */
        }
        /*
        Routlara Parametre Verme Aşağıdaki Gibidir Tip Tanımı Yapılır Route Üzerinde URL Şemasına Ekleme Yapılır
        navController.navigate("list/turkiye") şeklinde parametre vererek başka sayfaya geçiş yapabilirz
        */
        composable(
            "list/{name}", arguments = listOf(
                navArgument("name") {
                    type = NavType.StringType
                }
            )
        ) {
            val countryName =
                it.arguments?.getString("name")!!/* Sayfaya Gelen Parametreyi Bu Şekilde Yakalayabiliriz */
            DetailScreen(country_name = countryName)
        }
    }
}


@Composable
fun ListScreen(navController: NavController) {
    val context = LocalContext.current
    val ulkeler =
        mutableListOf<String>("Turkiye", "Almanya", "Kanada", "Norvec", "Amerika", "Hollanda")
    Box(Modifier.fillMaxSize()) {
        LazyColumn {
            items(count = ulkeler.size) {
                OutlinedCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = ulkeler[it])
                        ElevatedButton(onClick = {
                            Toast.makeText(context, "Clicked: ${ulkeler[it]}", Toast.LENGTH_LONG)
                                .show()
                            navController.navigate("list/${ulkeler[it]}")
                        }) {
                            Text(text = "Click")
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun DetailScreen(country_name: String) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "Selected Country: $country_name")
        }
    }
}
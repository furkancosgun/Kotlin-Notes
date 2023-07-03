package com.example.materialdesign

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.gson.Gson

/*

    def nav_version = "2.6.0"

    implementation("androidx.navigation:navigation-compose:$nav_version")
    implementation 'com.google.code.gson:gson:2.8.9'
 */

data class IconModel(val icon: Int, val contentDescription: String)

@Composable
fun AppNavigationController() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "icons") {
        composable("icons") {
            IconListView(navController = navController)
        }
        composable("icons/{icon}", arguments = listOf(
            navArgument("icon") {
                type = NavType.StringType
            }
        )) {
            var jsonString = it.arguments?.getString("icon")!!
            println(jsonString)
            var icon = Gson().fromJson(jsonString, IconModel::class.java)
            IconDetailView(icon = icon)
        }
    }
}


@Composable
fun IconListView(navController: NavController) {
    val icons = mutableListOf<IconModel>(
        IconModel(R.drawable.ic_android, "Android"),
        IconModel(R.drawable.ic_search, "Search"),
        IconModel(R.drawable.ic_menu, "Menu"),
        IconModel(R.drawable.ic_school, "School"),
        IconModel(R.drawable.ic_star, "Star")
    )
    Box(Modifier.fillMaxSize()) {
        LazyColumn {
            items(count = icons.size) {
                Card(
                    Modifier
                        .padding(8.dp)
                        .clickable {
                            navController.navigate("icons/${Gson().toJson(icons[it])}")
                        }) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Icon(
                            painter = painterResource(id = icons[it].icon),
                            contentDescription = icons[it].contentDescription
                        )
                        Text(text = icons[it].contentDescription)
                    }
                }
            }
        }
    }
}

@Composable
@Preview(showSystemUi = true)
fun IconDetailView(icon: IconModel = IconModel(icon = R.drawable.ic_school, "School")) {
    Card(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Icon(
                modifier = Modifier.sizeIn(minWidth = 240.dp, minHeight = 240.dp),
                painter = painterResource(id = icon.icon),
                contentDescription = icon.contentDescription
            )
            Text(text = icon.contentDescription, fontWeight = FontWeight.Bold)
        }
    }
}

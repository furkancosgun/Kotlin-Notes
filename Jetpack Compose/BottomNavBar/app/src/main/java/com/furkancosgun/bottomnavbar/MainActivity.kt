package com.furkancosgun.bottomnavbar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.furkancosgun.bottomnavbar.ui.theme.BottomNavBarTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BottomNavBarTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()
                    Scaffold(bottomBar = {
                        BottomNavigationBar(
                            items = listOf(
                                BottomNavItem(
                                    name = "home",
                                    route = "home",
                                    icon = Icons.Default.Home
                                ), BottomNavItem(
                                    name = "chat",
                                    route = "chat",
                                    icon = Icons.Default.Notifications
                                ), BottomNavItem(
                                    name = "settings",
                                    route = "settings",
                                    icon = Icons.Default.Settings
                                )
                            ),
                            navController = navController,
                            modifier = Modifier,
                            onItemClick = {
                                navController.navigate(it.route)
                            }
                        )
                    }) {
                        NavigationScreen(navController = navController)
                    }
                }
            }
        }
    }
}

@Composable
fun NavigationScreen(navController: NavHostController) {

    //Navigation Managment
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScren()
        }
        composable("chat") {
            ChatScren()
        }
        composable("settings") {
            SettingsScren()
        }
    }
}

@Composable
fun BottomNavigationBar(
    items: List<BottomNavItem>,//Navbar List
    navController: NavController,
    modifier: Modifier,
    onItemClick: (BottomNavItem) -> Unit
) {
    val backStackEntry = navController.currentBackStackEntryAsState()

    //Navbar oluştrulur itemlar atanır
    BottomNavigation(modifier = modifier, backgroundColor = Color.DarkGray, elevation = 5.dp) {
        items.forEach {
            val selected = it.route == backStackEntry.value?.destination?.route
            BottomNavigationItem(
                selected = selected,
                selectedContentColor = Color.Red,
                unselectedContentColor = Color.Black,
                onClick = { onItemClick(it) },
                icon = {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        if (it.badgeCount > 0) {
                            BadgedBox(badge = {
                                Text(text = it.badgeCount.toString())
                            }) {
                                Icon(it.icon, contentDescription = null)
                            }
                        } else {
                            Icon(it.icon, contentDescription = null)
                        }
                        if (selected) {
                            Text(text = it.name, textAlign = TextAlign.Center, fontSize = 16.sp)
                        }
                    }
                }
            )
        }
    }

}

@Composable
fun HomeScren() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = "Home")
    }
}

@Composable
fun ChatScren() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = "Chat")
    }
}

@Composable
fun SettingsScren() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = "Settings")
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BottomNavBarTheme {

    }
}
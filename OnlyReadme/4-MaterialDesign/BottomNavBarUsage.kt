package com.example.materialdesign

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun FirstScreen() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "First Screen", color = Color.Red)
    }
}

@Composable
fun SecondScreen() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Second Screen", color = Color.Red)
    }
}

data class Screen(val icon: Int, val title: String)


@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun BottomNavbarUsage() {
    val screens = mutableListOf(
        Screen(R.drawable.ic_school, "First Screen"),
        Screen(R.drawable.ic_menu, "Second Screen")
    )
    var selectedIndex by remember {
        mutableStateOf(0)
    }

    Scaffold(
        bottomBar = {
            BottomAppBar {
                screens.forEachIndexed { index, screen ->
                    NavigationBarItem(
                        selected = selectedIndex == index,
                        onClick = { selectedIndex = index },
                        label = { Text(text = screen.title) },
                        icon = {
                            Icon(
                                painter = painterResource(id = screen.icon),
                                contentDescription = screen.title
                            )
                        }
                    )
                }
            }
        }, content = {
            Box(modifier = Modifier.padding(it)) {
                if (selectedIndex == 0) {
                    FirstScreen()
                } else {
                    SecondScreen()
                }
            }
        }
    )
}
package com.example.materialdesign

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.launch

@Composable
fun First_Screen() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "First Screen", color = Color.Red)
    }
}

@Composable
fun Second_Screen() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Second Screen", color = Color.Red)
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview(showSystemUi = true)
fun DrawerUsage() {
    var screenList = listOf("First Screen", "Second Screen")
    var selectedIndex by remember {
        mutableStateOf(0)
    }
    var drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                screenList.forEachIndexed { index: Int, s: String ->
                    NavigationDrawerItem(
                        label = { Text(text = s) },
                        icon = {
                               if (selectedIndex == 0){
                                   Icon(painter = painterResource(id = R.drawable.ic_android), contentDescription = "First ")
                               }else{
                                   Icon(painter = painterResource(id = R.drawable.ic_school), contentDescription = "Second ")
                               }
                        },
                        selected = index == selectedIndex,
                        onClick = {
                            selectedIndex = index
                            scope.launch {
                                drawerState.close()
                            }
                        })
                }
            }
        },
        content = {
            if (selectedIndex == 0) {
                First_Screen()
            } else {
                Second_Screen()
            }
        }
    )

    /*
    Geri Tuşuna Basılırsa Nolcak
     */
    val activity = (LocalContext.current) as Activity
    BackHandler{
        if(drawerState.isOpen){
            scope.launch { drawerState.close() }
        }else{
            scope.launch { drawerState.open() }
        }
    }
}

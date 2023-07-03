package com.example.kullanicietkilesim

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showSystemUi = true)
@Composable
fun SnackbarUsage() {
    /*
    Snackbar Kullanmak İçin Scaffold İhtiyacımız vardır
     */
    var snackbarHostState = remember { SnackbarHostState() }//Snackbar Yönetimi İçin Kullanılan Yapı
    val scope =
        rememberCoroutineScope()//Coroutine kullanarak burdaki işlemin yönetimini sisteme bırakırız bu şekilde daha performanslı olur
    Scaffold(content = {
        Column(
            Modifier
                .fillMaxSize()
                .padding(it),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Button(onClick = {
                scope.launch {
                    snackbarHostState.showSnackbar("Message Area")
                }
            }) {
                Text(text = "Default")
            }
            Button(onClick = {
                scope.launch {
                    val snack = snackbarHostState.showSnackbar("Meesage Area", actionLabel = "YES")
                    if (snack == SnackbarResult.ActionPerformed) {
                        snackbarHostState.showSnackbar("Clicked To YES")
                    }
                }
            }) {
                Text(text = "With Action")
            }
            Button(onClick = { /*TODO*/ }) {
                Text(text = "Custom")
            }
        }
    }, snackbarHost = {
        SnackbarHost(hostState = snackbarHostState)
    })
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview(showSystemUi = true)
fun CustomSnackbarUsage() {
    val snackbarHostState = SnackbarHostState()
    val scope = rememberCoroutineScope()
    Scaffold(content = {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it), contentAlignment = Alignment.Center
        ) {
            Button(onClick = {
                scope.launch {
                    snackbarHostState.showSnackbar("Message Area", "YES")
                }
            }) {
                Text(text = "Show Custom Snackbar")
            }
        }
    }, snackbarHost = {
        SnackbarHost(hostState = snackbarHostState) {
            Snackbar(
                snackbarData = it,
                containerColor = Color.Magenta,//Arka Plan
                contentColor = Color.Red,//Message Rengi
                actionColor = Color.Blue,//Action Rengi

            )
        }
    })
}


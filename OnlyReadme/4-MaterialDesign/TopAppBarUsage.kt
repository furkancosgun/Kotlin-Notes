package com.example.materialdesign

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarUsage() {
    Scaffold(topBar = {
        TopAppBar(title = {
            Column {
                Text(text = "TopAppBar")
                Text(text = "Usage", fontSize = 16.sp)
            }
        }, colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.Red))
    }, content = {
        Column(modifier = Modifier.padding(it)) {
            Text(text = "Center")
        }
    })
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarWithActionUsage() {
    val context = LocalContext.current
    Scaffold(topBar = {
        TopAppBar(title = {
            Column {
                Text(text = "TopAppBar")
                Text(text = "Usage", fontSize = 16.sp)
            }
        },
            actions = {
                Button(onClick = {
                    Toast.makeText(context, "Action 1 Clicked", Toast.LENGTH_LONG)
                        .show()
                }) {
                    Text(text = "Action_1")
                }
                IconButton(onClick = {
                    Toast.makeText(context, "Action 2 Clicked", Toast.LENGTH_LONG)
                        .show()
                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_android),
                        contentDescription = "Android"
                    )
                }
            })
    }, content = {
        Column(modifier = Modifier.padding(it)) {
            Text(text = "Center")
        }
    })
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarWithMenuUsage() {
    var isOpenedMenu by remember {
        mutableStateOf(false)
    }
    var selectedCity by remember {
        mutableStateOf("")
    }
    val cities = mutableListOf("Istanbul", "Ankara", "Nevsehir", "Izmır")

    Scaffold(topBar = {
        TopAppBar(title = {
            Column {
                Text(text = "TopAppBar")
                Text(text = "Usage", fontSize = 16.sp)
            }
        },
            actions = {
                Box {
                    IconButton(onClick = { isOpenedMenu = !isOpenedMenu }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_menu),
                            contentDescription = "Menul"
                        )
                    }
                    DropdownMenu(
                        expanded = isOpenedMenu,
                        onDismissRequest = { isOpenedMenu = false }) {
                        cities.forEachIndexed { _: Int, value: String ->
                            DropdownMenuItem(text = { Text(text = value) }, onClick = {
                                selectedCity = value
                                isOpenedMenu = false
                            })
                        }
                    }
                }

            })
    }, content = {

        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Selected City: $selectedCity", fontWeight = FontWeight.Bold)
        }
    })
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview(showSystemUi = true)
fun TopAppBarWithSearchUsage() {
    var searchedText by remember {
        mutableStateOf("")
    }
    var isOpenedField by remember {
        mutableStateOf(true)
    }

    Scaffold(topBar = {
        TopAppBar(title = {
            if (isOpenedField) {
                OutlinedTextField(
                    value = searchedText,
                    onValueChange = { searchedText = it })
            } else {
                Text(text = "Title")
            }
        },
            actions = {

                IconButton(onClick = { isOpenedField = !isOpenedField }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_search),
                        contentDescription = "Menul"
                    )
                }
            })
    }, content = {

        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Searched Text: $searchedText", fontWeight = FontWeight.Bold)
        }
    })
}


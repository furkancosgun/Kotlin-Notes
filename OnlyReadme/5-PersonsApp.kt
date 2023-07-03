package com.example.kisileruygulamasi

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.kisileruygulamasi.ui.theme.KisilerUygulamasiTheme
import com.google.gson.Gson

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KisilerUygulamasiTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavigationController()
                }
            }
        }
    }
}

data class Person(val id: Int, val name: String, val telephone: String)

    @Composable
    fun NavigationController() {
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = "list") {
            composable("list") {
                ListScreen(navController)
            }
            composable("create") {
                CreateScreen()
            }
            composable(
                "update/{person}", arguments = listOf(
                    navArgument("person") {
                        type = NavType.StringType
                    }
                )
            ) {
                val personJson = it.arguments?.getString("person")!!
                UpdateScreen(person = Gson().fromJson(personJson, Person::class.java))
            }
        }
    }


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListScreen(navController: NavController) {
    var isSearching by remember {
        mutableStateOf(false)
    }
    var searchedText by remember {
        mutableStateOf("")
    }
    val personList = mutableListOf<Person>(
        Person(1, "Furkan", "+90 123 456 78 90"),
        Person(2, "Hasan", "+90 123 456 78 90")
    )
    val context = LocalContext.current
    Scaffold(
        topBar = {
            TopAppBar(title = {
                if (isSearching) {
                    TextField(value = searchedText, onValueChange = { searchedText = it })
                } else {
                    Text(text = "Persons App")
                }
            }, actions = {
                IconButton(onClick = { isSearching = !isSearching }) {
                    if (isSearching) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_close),
                            contentDescription = "Close"
                        )
                    } else {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_search),
                            contentDescription = "Search"
                        )
                    }
                }
            })
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate("create")
                }) {
                Icon(painter = painterResource(id = R.drawable.ic_add), contentDescription = "Add")
            }
        },
        content = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
            ) {
                LazyColumn {
                    items(count = personList.size) { index ->
                        ElevatedCard(modifier = Modifier
                            .padding(8.dp)
                            .clickable {
                                navController.navigate("update/${Gson().toJson(personList[index])}")
                            }) {
                            Row(
                                Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(text = "${personList[index].name} - ${personList[index].telephone}")
                                IconButton(onClick = {
                                    Toast.makeText(
                                        context,
                                        "Delete : ${personList[index].id}",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.ic_delete),
                                        contentDescription = "Delete Person"
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateScreen() {
    var name by remember {
        mutableStateOf("")
    }
    var telephone by remember {
        mutableStateOf("")
    }

    //Geri Tuşuna Bastıgında Onceki Sayfaya Geçmesi İçin Hali Hazırda Basıldıgında Once Textfielddan cıkar sonra gecer
    var focusManager = LocalFocusManager.current
    Scaffold(topBar = {
        TopAppBar(title = {
            Text(text = "Add Person")
        })
    }) { it ->
        Column(
            Modifier
                .fillMaxSize()
                .padding(it),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                placeholder = { Text(text = "John Doe", color = Color.Gray) })
            OutlinedTextField(
                value = telephone,
                onValueChange = { telephone = it },
                placeholder = { Text(text = "+90 123 456 78 90", color = Color.Gray) })
            OutlinedButton(onClick = {
                //Save Yapılınca Texfieldardaki focusu kaldırır
                focusManager.clearFocus()
            }) {
                Text(text = "Save")
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateScreen(person: Person) {
    var name by remember {
        mutableStateOf("")
    }
    var telephone by remember {
        mutableStateOf("")
    }

    //Sayfa Açıldıgı Anca Çalışan Bir Yaşam Dongüsü Fonksiyonu
    LaunchedEffect(key1 = true) {
        name = person.name
        telephone = person.telephone
    }
    //Geri Tuşuna Bastıgında Onceki Sayfaya Geçmesi İçin Hali Hazırda Basıldıgında Once Textfielddan cıkar sonra gecer
    var focusManager = LocalFocusManager.current
    Scaffold(topBar = {
        TopAppBar(title = { Text(text = "Update Person") })
    }) { it ->
        Column(
            Modifier
                .fillMaxSize()
                .padding(it),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                placeholder = { Text(text = "John Doe", color = Color.Gray) })
            OutlinedTextField(
                value = telephone,
                onValueChange = { telephone = it },
                placeholder = { Text(text = "+90 123 456 78 90", color = Color.Gray) })
            OutlinedButton(onClick = {
                focusManager.clearFocus()
            }) {
                Text(text = "Save")
            }
        }
    }
}

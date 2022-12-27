package com.furkancosgun.textfields_buttons_snackbar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalProvider
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.furkancosgun.textfields_buttons_snackbar.ui.theme.TextFieldsButtonsSnackbarTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TextFieldsButtonsSnackbarTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun MainScreen() {

    //Texti tutcak stateimiz
    val textState = remember {
        mutableStateOf("")
    }

    //Scaffold state kullanarak uygulama içindeki bazı ozel yapılara erişiriz
    //scaffold gibi
    val scaffoldState = rememberScaffoldState()

    //Coroutinler ana uygulamayı yormasın daha performanslı çalışsın diye
    //mainn thread uzerinde calışır ve main threadı bloklamazlar
    val coroutineScope = rememberCoroutineScope()

    Scaffold(scaffoldState = scaffoldState) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 10.dp)
        ) {
           OutlinedTextField(//Material design kutuphanesinin textfieldi
               value = textState.value,
               label = {
                   Text(text = "Enter Your Name")//Placeholder
               },
           onValueChange = {
               textState.value = it//Text değiştiginde tekrar atama yaparız
           },
               singleLine = true,//Tek satır olma durumu
               modifier = Modifier.fillMaxWidth()
           )
            Spacer(modifier = Modifier.height(32.dp))//Araya boşluk
            Button(onClick = {//Butona basıldıgında

                //Main thread uzeirnde coroutine scope açılarak
                //uygulamayı yormadan işlemler yaparız ve main threadı darlamayaız
                coroutineScope.launch {
                    //Scaffold state yardımıyla bir snackbar açarız
                    scaffoldState.snackbarHostState.showSnackbar("Hello ${textState.value}")
                }
            }) {
                Text(text = "Say Hello!")//Button Texti
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    TextFieldsButtonsSnackbarTheme {
        MainScreen()
    }
}
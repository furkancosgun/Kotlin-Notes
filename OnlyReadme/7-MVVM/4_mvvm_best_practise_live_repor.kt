package com.example.mvvm.MVVMLiveDataWithRepo

import androidx.lifecycle.MutableLiveData

class MathRepository {

    //Live Data Olutşturuldu
    private var result: MutableLiveData<Int> = MutableLiveData<Int>(0)

    fun getResult(): MutableLiveData<Int> {
        return result
    }

    fun sum(firstNumber: String, secondNumber: String) {
        try {
            result.value = (firstNumber.toInt() + secondNumber.toInt())
        } catch (e: Exception) {
            result.value = 0
        }
    }

    fun extract(firstNumber: String, secondNumber: String) {
        try {
            result.value = (firstNumber.toInt() - secondNumber.toInt())
        } catch (e: Exception) {
            result.value = 0
        }
    }

    fun multiply(firstNumber: String, secondNumber: String) {
        try {
            result.value = (firstNumber.toInt() * secondNumber.toInt())
        } catch (e: Exception) {
            result.value = 0
        }
    }

    fun division(firstNumber: String, secondNumber: String) {
        try {
            result.value = (firstNumber.toInt() / secondNumber.toInt())
        } catch (e: Exception) {
            result.value = 0
        }
    }
}


package com.example.mvvm.MVVMLiveDataWithRepo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


//ViewModel
//implementation("androidx.navigation:navigation-compose:2.6.0")
//implementation "androidx.compose.runtime:runtime-livedata:1.6.0-alpha01"
class MathViewModel : ViewModel() {

    var result = MutableLiveData<Int>()
    var mRepo = MathRepository()

    init {
        result = mRepo.getResult()
    }

    fun sumNumbers(firstNumber: String, secondNumber: String) {
        mRepo.sum(firstNumber, secondNumber)
    }

    fun extNumbers(firstNumber: String, secondNumber: String) {
        mRepo.extract(firstNumber, secondNumber)
    }

    fun mulNumbers(firstNumber: String, secondNumber: String) {
        mRepo.multiply(firstNumber, secondNumber)
    }

    fun divNumbers(firstNumber: String, secondNumber: String) {
        mRepo.division(firstNumber, secondNumber)
    }
}


package com.example.mvvm.MVVMLiveDataWithRepo

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel


@OptIn(ExperimentalMaterial3Api::class)
@Preview(showSystemUi = true)
@Composable
fun MathView() {

    var firstnumber by remember {
        mutableStateOf("")
    }
    var secondnumber by remember {
        mutableStateOf("")
    }
    val mathViewModel: MathViewModel = viewModel()//Büyük Küçük Harfe Dikkat et
    val result = mathViewModel.result.observeAsState(0)//Viewmodel içindeki livedatayı gözlemler
    Scaffold {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = "${result.value}",
                    fontWeight = FontWeight.Bold,
                    fontSize = 32.sp
                )

                TextField(value = firstnumber, onValueChange = { t -> firstnumber = t }, label = {
                    Text(text = "First Number")
                }, keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                )
                )
                TextField(value = secondnumber, onValueChange = { t -> secondnumber = t }, label = {
                    Text(text = "Second Number")
                }, keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                )
                )
                Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    Button(onClick = {
                        mathViewModel.sumNumbers(firstnumber, secondnumber)
                    }) {
                        Text(text = "+")
                    }
                    Button(onClick = {
                        mathViewModel.extNumbers(firstnumber, secondnumber)
                    }) {
                        Text(text = "-")
                    }
                    Button(onClick = {
                        mathViewModel.mulNumbers(firstnumber, secondnumber)
                    }) {
                        Text(text = "*")
                    }
                    Button(onClick = {
                        mathViewModel.extNumbers(firstnumber, secondnumber)
                    }) {
                        Text(text = "/")
                    }
                }
            }
        }
    }
}


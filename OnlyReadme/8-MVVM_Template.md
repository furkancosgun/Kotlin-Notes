# Implementation
```gradle
//build.gradle(:app)

//MVVM
implementation("androidx.navigation:navigation-compose:2.6.0")

//Observer
implementation "androidx.compose.runtime:runtime-livedata:1.6.0-alpha01"
```

# Repository(Optional)

```kotlin
package com.example.mvvm

import androidx.lifecycle.MutableLiveData

class CounterRepository {
    private var count = MutableLiveData<Int>()

    //Default Value
    init {
        count = MutableLiveData<Int>(0)
    }  

    //Count live data returner
    fun getCounter(): MutableLiveData<Int> {
        return count
    }

    fun increment() {
        count.value = count.value?.plus(1)
    }

    fun decrement() {
        count.value = count.value?.minus(1)
    }
}
```

# ViewModel
```kotlin
package com.example.mvvm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CounterViewModel : ViewModel() {
    var count = MutableLiveData<Int>(0)
    private var counterRepository = CounterRepository()

    init {
        count = counterRepository.getCounter()
    }

    fun handleIncrement() {
        counterRepository.increment()
    }

    fun handleDecrement() {
        counterRepository.decrement()
    }

}
```

# View

```kotlin

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun CounterView() {
    val counterViewModel: CounterViewModel = viewModel()
    val count = counterViewModel.count.observeAsState()
    Scaffold {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "${count.value}", fontWeight = FontWeight.Bold)
                Button(onClick = {
                    counterViewModel.handleIncrement()
                }) {
                    Text(text = "+")
                }
                Button(onClick = {
                    counterViewModel.handleDecrement()
                }) {
                    Text(text = "-")
                }
            }
        }
    }
}
```
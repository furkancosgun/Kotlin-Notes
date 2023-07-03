# Obje Ve Listelerde LiveData Update Ve Observe Etmek

- ### Implemtation

  ```kotlin
  //MVVM
  implementation("androidx.navigation:navigation-compose:2.6.0")

  //Observer
  implementation "androidx.compose.runtime:runtime-livedata:1.6.0-alpha01"
  ```

- ### Model

  ```kotlin
    package com.example.loginviewmvvm

    data class User(var id:Int,var name:String,var surname:String,var password:String){
        override fun toString(): String {
            return "$id - $name $surname  $password"
        }
    }
  ```

- ### ViewModel

  ```kotlin
    //MainViewModel.kt
    package com.example.loginviewmvvm

    import androidx.lifecycle.MutableLiveData
    import androidx.lifecycle.ViewModel

    class MainViewModel:ViewModel() {
        val user = MutableLiveData<User>()
        val userList = MutableLiveData<List<User>>()
        init {
            user.value = (User(0,"","",""))
            userList.value = listOf()
        }

        fun setName(text: String) {
            val updatedUser = user.value?.copy(name = text)
            user.value = updatedUser
        }

        fun setSurname(text: String) {
            val updatedUser = user.value?.copy(surname = text)
            user.value = updatedUser
        }

        fun setPassword(text: String) {
            val updatedUser = user.value?.copy(password = text)
            user.value = updatedUser
        }

        fun addNewUser(newUser: User){
            val updatedUser : User
            if (userList.value!!.isNotEmpty()){
                updatedUser = newUser.copy(id = userList.value?.maxOf { it.id + 1 } ?: 0)
            }else{
                updatedUser = newUser.copy(id = userList.value?.size ?: 0)
            }
            userList.value = userList.value!! + updatedUser
        }

        fun removeUser(newUser: User){
            userList.value = userList.value!!.filter{it.id!=newUser.id}
        }
    }
  ```

- ### View

  ```kotlin
    //MainView.kt
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    @Preview(showSystemUi = true)
    fun MainView(){
        val viewModel : MainViewModel = viewModel()
        val context = LocalContext.current

        val userFields = viewModel.user.observeAsState().value!!
        val userList = viewModel.userList.observeAsState().value!!
        Box(modifier = Modifier.fillMaxSize()){
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(8.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(16.dp)) {
                TextField(value = userFields.name, onValueChange ={
                    viewModel.setName(it)
                }, label = {
                    Text(text = "Name")
                })
                TextField(value = userFields.surname, onValueChange ={
                    viewModel.setSurname(it)
                }, label = {
                    Text(text = "Surname")
                })
                TextField(value = userFields.password, onValueChange ={
                    viewModel.setPassword(it)
                }, label = {
                    Text(text = "Password")
                })
                Button(onClick = {
                    viewModel.addNewUser(userFields)
                    Toast.makeText(context, "OK", Toast.LENGTH_SHORT).show()
                }) {
                    Text(text = "ADD TO LİST")
                }
                LazyColumn{
                    items(userList.size){
                        Card {
                            Row(
                                Modifier
                                    .fillMaxWidth()
                                    .padding(8.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(text = userList[it].toString())
                                Button(onClick = { viewModel.removeUser(userList[it]) }) {
                                    Text(text = "Delete")
                                }
                            }
                        }
                    }
                }
            }
        }
    }
  ```

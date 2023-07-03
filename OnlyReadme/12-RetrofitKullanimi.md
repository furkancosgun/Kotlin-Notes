# Retrofit Kullanımı

### Implementation

```gradle
//Retrofit
implementation 'com.squareup.retrofit2:retrofit:2.9.0'

//Json String To Object Or Object To Json String
implementation 'com.squareup.retrofit2:converter-gson:2.9.0'

//MVVM
implementation("androidx.navigation:navigation-compose:2.6.0")

//Observer
implementation "androidx.compose.runtime:runtime-livedata:1.6.0-alpha01"
```

### Create Model For Response

```kotlin
package com.example.retrofitkullanimi

data class Person(
    val id: Int,
    val name: String,
    val telephone: String
) {
    override fun toString(): String {
        return "$name $telephone"
    }
}
```

### Create DAO(DATA ACCESS OBJECT)

```kotlin
package com.example.retrofitkullanimi

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface PersonDAO {

    @GET("persons/api")
    fun getAllPersons(): Call<List<Person>>

    @POST("persons/api")
    fun savePerson(@Body person: Person): Call<Person>

    @GET("persons/api/{userId}")
    fun getPersonById(@Path("userId") userId: Int): Call<Person>

    @PUT("persons/api/{userId}")
    fun updatePerson(@Path("userId") userId: Int, @Body person: Person): Call<Person>

    @DELETE("persons/api/{userId}")
    fun deletePerson(@Path("userId") userId: Int): Call<String>

}

```

### Create Retrofit Client For Custom Client

```kotlin
package com.example.retrofitkullanimi

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {
    companion object {
        fun getClient(baseUrl: String): Retrofit {
            return Retrofit
                .Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}
```

### Create Api Util For Retrofit And DAO Handler

```kotlin
package com.example.retrofitkullanimi

class APiUtils {
    companion object {
        private const val BASE_URL = "http://10.0.2.2:8000/"
        fun getPersonDAO(): PersonDAO {
            return RetrofitClient.getClient(BASE_URL).create(PersonDAO::class.java)
        }
    }
}
```

### Create Repository

```kotlin
package com.example.retrofitkullanimi

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PersonRepository {
    private val personList = MutableLiveData<List<Person>>(emptyList())
    private val personDAO = APiUtils.getPersonDAO()

    fun getLiveData(): MutableLiveData<List<Person>> {
        return personList
    }

    /*LIST
    [
        {
            "id": 6,
            "name": "Aasdads",
            "telephone": "+90 876 543 21 00"
        }
    ]
    */
    fun getAllPersons() {
        CoroutineScope(Dispatchers.Main).launch {
            personDAO.getAllPersons().enqueue(object : Callback<List<Person>> {
                override fun onResponse(
                    call: Call<List<Person>>,
                    response: Response<List<Person>>
                ) {
                    response.body()?.let {
                        personList.value = it
                    }
                }

                override fun onFailure(call: Call<List<Person>>, t: Throwable) {
                    personList.value = listOf()
                    t.printStackTrace()
                }
            })
        }
    }

    /*OBJECT
    {
        "id": 6,
        "name": "Aasdads",
        "telephone": "+90 876 543 21 00"
    }
    */
    fun updatePerson(person: Person) {
        CoroutineScope(Dispatchers.IO).launch {
            personDAO.updatePerson(userId = person.id, person).enqueue(object : Callback<Person> {
                override fun onResponse(call: Call<Person>, response: Response<Person>) { getAllPersons()}
                override fun onFailure(call: Call<Person>, t: Throwable) {}
            })
        }
    }


    /*OBJECT
    {
        "id": 6,
        "name": "Aasdads",
        "telephone": "+90 876 543 21 00"
    }
    */
    fun savePerson(person: Person) {
        CoroutineScope(Dispatchers.IO).launch {
            personDAO.savePerson(person).enqueue(object : Callback<Person> {
                override fun onResponse(call: Call<Person>, response: Response<Person>) { getAllPersons()}
                override fun onFailure(call: Call<Person>, t: Throwable) {}
            })
        }
    }

    /*OBJECT|STRING
    {
        "res":".....",
    }
    */
    fun deletePerson(personId: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            personDAO.deletePerson(personId).enqueue(object : Callback<String> {
                override fun onResponse(call: Call<String>, response: Response<String>) { getAllPersons() }
                override fun onFailure(call: Call<String>, t: Throwable) {}
            })
        }
    }
}
```

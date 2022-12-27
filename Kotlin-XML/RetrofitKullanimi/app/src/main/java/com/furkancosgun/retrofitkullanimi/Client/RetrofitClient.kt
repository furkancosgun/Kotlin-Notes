package com.furkancosgun.retrofitkullanimi.Client

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {
    companion object {
        //Statik
        fun getClient(baseUrl: String): Retrofit {
            return Retrofit.Builder()
                .baseUrl(baseUrl)//Base Url
                .addConverterFactory(GsonConverterFactory.create())//Url den donen degeri direk modellemek için kullanırız
                .build()
        }
    }
}
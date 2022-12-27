package com.furkancosgun.retrofitkullanimi.Utilities

import com.furkancosgun.retrofitkullanimi.Client.RetrofitClient
import com.furkancosgun.retrofitkullanimi.DAO.PostsDAOInterface

class APIUtil {
    companion object {
        val baseUrl = "https://jsonplaceholder.typicode.com/"

        fun getPostsDAOInterface(): PostsDAOInterface {
            return RetrofitClient.getClient(baseUrl).create(PostsDAOInterface::class.java)
        }
    }
}